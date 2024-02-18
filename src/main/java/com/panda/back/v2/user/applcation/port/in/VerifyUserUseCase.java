package com.panda.back.v2.user.applcation.port.in;

import com.panda.back.v2.user.applcation.port.in.dto.SendVerificationCodeDto;
import com.panda.back.v2.user.applcation.port.in.dto.VerifyEmailDto;

public interface VerifyUserUseCase {

  //sendEmail
  SendVerificationCodeDto sendEmail(SendVerificationCodeDto sendVerificationCodeDto);

  //verify
  VerifyEmailDto verify(VerifyEmailDto verifyEmailDto);

}
