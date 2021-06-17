package com.example.designtopicselectionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // 开启基于注解的异步任务支持
@SpringBootApplication
public class DesignTopicSelectionSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DesignTopicSelectionSystemApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DesignTopicSelectionSystemApplication.class);
    }
}
