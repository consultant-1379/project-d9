package com.example.projectd9spring;

import java.time.LocalDateTime;

public class JenkinsBuild {
    private int id;
    private LocalDateTime timestamp;
    private Boolean result;

    public JenkinsBuild(int id, LocalDateTime timestamp, Boolean result) {
        this.id = id;
        this.timestamp = timestamp;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "JenkinsBuild{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", result=" + result +
                '}';
    }
}
