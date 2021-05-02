package com.isep.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProjectApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
