package com.wanglj.exercise;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Wanglj
 * @date 2021/7/21 15:47
 */
@SpringBootApplication
@EnableScheduling
public class ExerciseSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ExerciseSpringBootApplication.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);

//        SpringApplication.run(ExerciseSpringBootApplication.class, args);


    }
}
