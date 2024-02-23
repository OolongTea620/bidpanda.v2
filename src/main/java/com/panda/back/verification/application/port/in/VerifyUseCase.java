package com.panda.back.verification.application.port.in;

import com.panda.back.verification.application.port.in.dto.VerifyEmailDto;

public interface VerifyUseCase {
  void sendEmail(String email);
  VerifyEmailDto verify(VerifyEmailDto verifyEmailDto);

}
