package com.panda.back.v2.user.application.port.in;

import com.panda.back.v2.user.application.port.in.dto.SendVerificationCodeDto;
import com.panda.back.v2.user.application.port.in.dto.VerifyEmailDto;

public interface VerifyUserUseCase {

  //sendEmail
  SendVerificationCodeDto sendEmail(SendVerificationCodeDto sendVerificationCodeDto);

  //verify
  VerifyEmailDto verify(VerifyEmailDto verifyEmailDto);

}
