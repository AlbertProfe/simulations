package com.example.demo.utils;

import com.example.demo.model.ImagePlayer;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.ImagePlayerService;
import com.example.demo.service.PlayerService;
import com.example.demo.service.TrafficTrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RunnerFillingDB implements ApplicationRunner {
    //https://javadzone.com/spring-boot-runners-commandline-vs-application/
    @Autowired
    PlayerService playerService;
    @Autowired
    TrafficTrialService trafficTrialService;
    @Autowired
    EnrollmentService enrollmentService;
    @Autowired
    ImagePlayerService imagePlayerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //populate_H2_DB();
        //testSimulationsMongoDB();
    }

    public void populate_H2_DB(){
        //simulationService.populate();
        trafficTrialService.populate();
        //universityService.populate();
        playerService.populate();
        enrollmentService.populate();

    }

    public void testSimulationsMongoDB() {

        //imagePlayerService.populate();

        List<ImagePlayer> images = imagePlayerService.findAllImages();

        for (ImagePlayer imagePlayer : images) {
            System.out.println(imagePlayer);
        }

        //imagePlayerService.deleteAllImages();

    }
}