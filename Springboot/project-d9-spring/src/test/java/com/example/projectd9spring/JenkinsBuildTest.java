package com.example.projectd9spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class JenkinsBuildTest {
    private JenkinsBuild jen;

    @BeforeEach
    public void createJenny(){
        jen = new JenkinsBuild(1, LocalDateTime.parse("2022-08-10T10:54:20.01"), true);
    }

    @Test
    public void testToString(){
        JenkinsBuild jen2 = new JenkinsBuild(1, LocalDateTime.parse("2022-08-10T10:54:20.01"), true);
        assertEquals(jen.toString(),jen2.toString());
    }
    @Test
    public void testId(){
        assertEquals(jen.getId(), 1);
    }
    @Test
    public void testTime(){
        assertEquals((jen.getTimestamp()), LocalDateTime.parse("2022-08-10T10:54:20.01"));
    }
    @Test
    public void testResult(){
        assertEquals(jen.getResult(),true);
    }
}