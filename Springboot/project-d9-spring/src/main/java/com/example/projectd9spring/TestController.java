package com.example.projectd9spring;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/getlastfifty")
@CrossOrigin
public class TestController {

//    @Autowired
//    private TestController jenkins;

    // later on this method will probably take a "JenkinsServer" object which already has an open connection to perform this operation, rather than one with my username and password hardcoded in
    @GetMapping(produces={"application/json", "application/xml"})
    public List<JenkinsBuild> getTest(String url, String jobName) throws IOException, URISyntaxException {

        JenkinsServer jenkins = new JenkinsServer(new URI(url),"eaardvc", "11a27e974a3fba383d1541ad1e44ee67f6");
        List<Build> builds = jenkins.getJob(jobName).getBuilds();

        List<JenkinsBuild> jBuilds = new ArrayList<>();

        for(Build temp : builds) {
            //System.out.println(new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(temp.details().getTimestamp()*1000)));
            LocalDateTime ldt = Instant.ofEpochMilli(temp.details().getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDateTime();

            // the below is just formatting, the date is workable with the above ^
//            currentBuildString = "Build Id: " + temp.details().getId() + " :: Date: " + ldt + " :: Build result: " + temp.details().getResult();
//            lastFiftyBuilds += "\r\n" + currentBuildString;


            try {
                String result = temp.details().getResult().toString() ;

                jBuilds.add(new JenkinsBuild(Integer.parseInt(temp.details().getId()), ldt, result.equals("SUCCESS") ? true : false));
            } catch (Exception e) {
                System.out.println(e);
              continue;
            }

        }
        return jBuilds;
    }

    @PostMapping(consumes = {"application/x-www-form-urlencoded", "application/json", "application/xml", "application/urlencoded", "application/x-www-form-urlencoded;charset=UTF-8"}, produces = {"application/json"})
    public ResponseEntity<FrontEndStats> testPost(@RequestBody JenkinsString jenkinsString) throws IOException, URISyntaxException {
        System.out.println(("----------------"));
        System.out.println(jenkinsString.getUrl());
        System.out.println(("----------------"));
        System.out.println(jenkinsString.getStartDate());
        System.out.println(("----------------"));
        System.out.println(jenkinsString.getEndDate());

        System.out.println(jenkinsString.getName());

        TestController jenkins = new TestController();
        MetricsCalculator metricsCalculator = new MetricsCalculator();
        List<JenkinsBuild> job = jenkins.getTest(jenkinsString.getUrl(), jenkinsString.getName());

        Double failureRate = metricsCalculator.buildFailureRate(job);
        Pair<Long, Double> recoveryTime = metricsCalculator.buildRecoveryTime(job);
        int failure = metricsCalculator.calcFailure(job, false);
        int success = metricsCalculator.calcFailure(job, true);
        System.out.println("Failures = " + failure);
        System.out.println("Passes = " + success);
        Metric metric = new Metric(failureRate, recoveryTime, success, failure);

        FrontEndStats frontEndStats = new FrontEndStats(job,metric);
        return ResponseEntity.ok().body(frontEndStats);
    }
}
