package com.panda.back.user.application.port.in.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePasswordDto {
  private String email;
  private String originPassword;
  private String changedPassword;

  @Builder
  public UpdatePasswordDto(String email, String originPassword, String changedPassword) {
    this.email = email;
    this.originPassword = originPassword;
    this.changedPassword = changedPassword;
  }
}
