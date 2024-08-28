package com.example.projectd9spring;

import com.example.projectd9spring.MetricsCalculator;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class DemoApplication {


	public DemoApplication() throws URISyntaxException {
	}

	public static void main(String[] args) throws IOException, URISyntaxException {

		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		System.out.println("hello");

		JenkinsServer jenkins = new JenkinsServer(new URI("https://fem1s11-eiffel216.eiffel.gic.ericsson.se:8443/jenkins"),"eaardvc", "11a27e974a3fba383d1541ad1e44ee67f6");
		List<Build> builds = jenkins.getJob("eric-oss-ran-topology-adapter_Publish").getBuilds();

		List<JenkinsBuild> jBuilds = new ArrayList<>();

		for(Build temp : builds) {
			//System.out.println(new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(temp.details().getTimestamp()*1000)));
			LocalDateTime ldt = Instant.ofEpochMilli(temp.details().getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDateTime();

			// the below is just formatting, the date is workable with the above ^
//            currentBuildString = "Build Id: " + temp.details().getId() + " :: Date: " + ldt + " :: Build result: " + temp.details().getResult();
//            lastFiftyBuilds += "\r\n" + currentBuildString;


			try {
				String result = temp.details().getResult().toString();

				jBuilds.add(new JenkinsBuild(Integer.parseInt(temp.details().getId()), ldt, result.equals("SUCCESS") ? true : false));
			} catch (Exception e) {
				System.out.println(e);
				continue;
			}

		}
		//System.out.println(MetricsCalculator.buildFailureRate(jBuilds));
		//System.out.println(jBuilds);
		System.out.println(MetricsCalculator.buildRecoveryTime(jBuilds));
	}

}
