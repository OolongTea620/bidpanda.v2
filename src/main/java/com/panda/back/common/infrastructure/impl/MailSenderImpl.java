package com.panda.back.common.infrastructure.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
  private JavaMailSender javaMailSender;

  public void send(String email, String verificationCode) {
    String title = "Bid Panda 회원가입 인증 메일입니다.";
    String text = "인증번호는 " + verificationCode + "입니다. <br/>";

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject(title);
    message.setText(text);

    javaMailSender.send(message);
  }

  @Override
  public void send(SimpleMailMessage simpleMessage) throws MailException {
    MailSender.super.send(simpleMessage);
  }

  @Override
  public void send(SimpleMailMessage... simpleMessages) throws MailException {

  }
}
