package com.panda.back.common.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
  @Value("${spring.mail.protocol}")
  private String protocol;

  @Value("${spring.mail.properties.mail.smtp.auth}")
  private boolean auth;

  @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
  private boolean starttls;

  @Value("${spring.mail.host}")
  private String host;

  @Value("${spring.mail.port}")
  private int port;

  @Value("${spring.mail.username}")
  private String username;

  @Value("${spring.mail.password}")
  private String password;

  @Bean
  public JavaMailSender javaMailSender(){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    Properties properties = new Properties();
    properties.put("mail.transport.protocol", protocol);
    properties.put("mail.smtp.auth", auth);
    properties.put("mail.smtp.starttls.enable", starttls);

    mailSender.setHost(host);
    mailSender.setUsername(username);
    mailSender.setPassword(password);
    mailSender.setPort(port);
    mailSender.setJavaMailProperties(properties);

    return mailSender;
  }

}
