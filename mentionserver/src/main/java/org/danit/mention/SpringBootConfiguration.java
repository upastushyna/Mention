package org.danit.mention;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootConfiguration {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootConfiguration.class);
  }

}