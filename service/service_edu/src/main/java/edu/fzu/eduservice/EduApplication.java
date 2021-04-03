package edu.fzu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JohnCarraway
 * @create 2021-01-02 14:29
 */

@SpringBootApplication
@ComponentScan(basePackages = "edu.fzu")
@EnableFeignClients
@EnableDiscoveryClient
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class);
    }
}
