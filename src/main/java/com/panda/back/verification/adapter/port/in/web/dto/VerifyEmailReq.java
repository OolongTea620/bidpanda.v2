package com.panda.back.verification.adapter.port.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VerifyEmailReq {
  @Email
  @NotBlank
  private final String email;
  public VerifyEmailReq(String email) {
    this.email = email;
  }
}
