package br.com.springboot.api_rest_w_front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.ui.Model;

/**
 *
 * Spring Boot application starter class
 */
@EntityScan(basePackages= {"br.com.springboot.api_rest_w_front.model"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
