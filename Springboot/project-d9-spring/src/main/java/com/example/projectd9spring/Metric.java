package com.example.projectd9spring;

import org.javatuples.Pair;

public class Metric {
    private Double buildRecoveryTime;
    private Pair<Long, Double> buildFailureRate;
    private int success;
    private int failure;

    public Metric(Double buildRecoveryTime, Pair<Long, Double> buildFailureRate, int success, int failure){
        this.buildRecoveryTime = buildRecoveryTime;
        this.buildFailureRate = buildFailureRate;
        this.success = success;
        this.failure = failure;
    }

    public Double getBuildRecoveryTime() {
        return buildRecoveryTime;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    public Pair<Long, Double> getBuildFailureRate() {
        return buildFailureRate;
    }
}
