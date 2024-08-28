package com.example.projectd9spring;

import java.util.List;

public class FrontEndStats {
    private List<JenkinsBuild> builds;
    private Metric metrics;

    public FrontEndStats(List<JenkinsBuild> builds, Metric metrics){
        this.builds = builds;
        this.metrics = metrics;
    }

    public List<JenkinsBuild> getBuilds() {
        return builds;
    }

    public Metric getMetrics() {
        return metrics;
    }
}
