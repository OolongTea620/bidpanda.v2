package com.panda.back.v2.common.infrastructure;

public interface MailSender {
  void send(String email, String verificationCode);
}
