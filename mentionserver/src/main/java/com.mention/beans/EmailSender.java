package com.mention.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@PropertySource("config.properties")
@Component
public class EmailSender {

  @Value("${smtpLogin}")
  private String smtpLogin;

  @Value("${smtpPassword}")
  private String smtpPassword;

  @Value("${smtpHostName}")
  private String smtpHostName;

  @Value("${smtpPort}")
  private int smtpPort;

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(smtpHostName);
    mailSender.setPort(smtpPort);
    mailSender.setUsername(smtpLogin);
    mailSender.setPassword(smtpPassword);

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

    return mailSender;
  }
}
