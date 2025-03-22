package com.arpit.taskk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskkApplication.class, args);
    }

}
