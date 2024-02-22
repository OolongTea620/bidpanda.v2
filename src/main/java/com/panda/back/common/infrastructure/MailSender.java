package com.panda.back.common.infrastructure;

public interface MailSender {
  void send(String email, String verificationCode);
}
