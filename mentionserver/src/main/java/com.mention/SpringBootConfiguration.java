package com.mention;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootConfiguration {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootConfiguration.class);
  }

}