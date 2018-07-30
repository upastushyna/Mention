package com.mention;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAutoConfiguration(exclude = {JndiConnectionFactoryAutoConfiguration.class,DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan
public class SpringBootConfiguration {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootConfiguration.class);
  }

}