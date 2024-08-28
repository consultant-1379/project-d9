package com.example.projectd9spring;

public class JenkinsString {
    private String url;
    private String name;
    private String startDate;
    private String endDate;

    public JenkinsString(String url, String name, String startDate, String endDate){
        this.url = url;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUrl() {
        return url;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }
}
