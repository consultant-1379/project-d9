package com.example.projectd9spring;

import org.javatuples.Pair;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MetricsCalculator {


    public static Double buildFailureRate(List<JenkinsBuild> jbuilds) {

        HashMap<String, Double> results = new HashMap<String, Double>();
        // this hashmap is just to track the number of successes and fails
        results.put("Successes", 0.00);
        results.put("Fails", 0.00);

        // this loop iterates through the provided array to sum the successes and fails
        for(int i = 0; i < jbuilds.size(); i++) {
           if(jbuilds.get(i).getResult()){
               results.merge("Successes", 1.00, Double::sum);
           } else {results.merge("Fails", 1.00, Double::sum);}
        }

        // divide the fails by the sample size and divide by 100
        return results.get("Fails")/jbuilds.size() * 100;
    }
    public int calcFailure (List<JenkinsBuild> jbuilds, boolean resultCheck){
        int failure = 0;
        for(JenkinsBuild result: jbuilds){
            if(result.getResult() == resultCheck){
                failure += 1;
            }
        }
        return failure;
    }

    public static Pair<Long, Double> buildRecoveryTime(List<JenkinsBuild> jbuilds) {

        double sum = 0.0;
        double standardDeviation = 0.0;

        // the array below is going to store the time IN SECONDS between each failed build and the build after, which is presumed to have passed
        ArrayList<Long> time_between_builds = new ArrayList<>();
        for(int i = 1; i < jbuilds.size(); i++) {
            if(!jbuilds.get(i).getResult()){
                System.out.print(jbuilds.get(i));
                LocalDateTime n = jbuilds.get(i).getTimestamp();
                LocalDateTime nplusone = jbuilds.get(i-1).getTimestamp();
                long time_between = Duration.between(n, nplusone).getSeconds();
                sum += time_between;
                time_between_builds.add(time_between);
            }
        }

        // sorting the array is required to find the median value
        Collections.sort(time_between_builds);
        long medianSeconds = time_between_builds.get(time_between_builds.size()/2);
        double mean = sum/time_between_builds.size();

        // lastly, standard deviation is calculated
       for(Long times : time_between_builds) {
           standardDeviation += Math.pow(times - mean, 2);
       }
       return new Pair<Long, Double>(medianSeconds, Math.sqrt(standardDeviation/time_between_builds.size()));
    }
}
