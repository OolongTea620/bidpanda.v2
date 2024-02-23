package com.panda.back.verification.adapter.port.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VerifyCodeReq {
  @Email
  @NotBlank
  private String email;

  public VerifyCodeReq() {
  }

  public VerifyCodeReq(String email) {
    this.email = email;
  }
}
