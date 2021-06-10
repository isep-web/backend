package com.isep.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.isep.*")
public class ProjectApplication
{


    public static void main(String[] args)
    {
        SpringApplication.run(ProjectApplication.class, args);
    }


}
