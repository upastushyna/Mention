package com.mention;

import com.mention.Workflow.StringMakerOne;
import com.mention.Workflow.StringMakerTwo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConfiguration {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootConfiguration.class);
    StringMakerOne s1 = new StringMakerOne();
    StringMakerTwo s2 = new StringMakerTwo();
    System.out.println(s1.getStringOne() + " " + s2.getStringTwo());
  }

}