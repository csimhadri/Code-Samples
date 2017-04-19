package com.poc.rest.springboot;

//todo - code review
//- externalize API
//- add unit tests
//clean up Product Service
// docker
//swagger why .class import
//remove HelloController
// Other ids in redsky?
// cloud installation
// - understand how Mongo DB is setuo?
// logger
// productionalization
//Author
//gitHub
//Readme
//javadocs
// put and post are the same here - is that okay


import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({
        "com.poc.rest.springboot.domain",
        "com.poc.rest.springboot.swagger",
        "com.poc.rest.springboot.service",
        "com.poc.rest.springboot.controller"
})

public class Application {
    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(Application.class);
        sa.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}
