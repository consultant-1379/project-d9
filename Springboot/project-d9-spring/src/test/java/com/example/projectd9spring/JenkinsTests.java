package com.example.projectd9spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JenkinsTests {
    @Autowired
    JenkinsBuild jBuild;

    private JenkinsBuild jen;
    @BeforeEach
    public void createJenny(){
        jen = new JenkinsBuild(1, LocalDateTime.parse("2022-08-10T10:54:20.01"), true);
    }
    @Test
    public void testJenny() throws IOException, URISyntaxException {


        TestController jensen = mock(TestController.class);
        List<JenkinsBuild> jBuilds = new ArrayList<>();
        jBuilds.add(jen);

        List<JenkinsBuild> jBuild2 = new ArrayList<JenkinsBuild>();
        jBuild2.add(jen);
        when(jensen.getTest("http", "job")).thenReturn(jBuild2);
        System.out.println(jBuilds);
        assertEquals(jBuild2, jBuilds);


    }
}
