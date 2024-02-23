package com.panda.back.verification.adapter.port.in.web.dto;

import com.panda.back.verification.application.port.in.dto.VerifyEmailDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VerifyEmailReq {

  @NotBlank
  @Email
  private final String email;

  @NotBlank
  private final String verificationCode;

  public VerifyEmailReq(String email, String verificationCode) {
    this.email = email;
    this.verificationCode = verificationCode;
  }

  public VerifyEmailDto toDto() {
    return VerifyEmailDto.builder()
        .email(email)
        .verificationCode(verificationCode)
        .build();
  }
}
