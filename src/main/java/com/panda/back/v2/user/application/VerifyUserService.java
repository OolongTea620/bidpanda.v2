package com.panda.back.v2.user.application;

import com.panda.back.v2.common.exception.user.AlreadyExistsUserException;
import com.panda.back.v2.common.exception.user.VerifyUserException;
import com.panda.back.v2.common.infrastructure.MailSender;
import com.panda.back.v2.common.infrastructure.RandomHolder;
import com.panda.back.v2.user.application.port.in.VerifyUserUseCase;
import com.panda.back.v2.user.application.port.in.dto.SendVerificationCodeDto;
import com.panda.back.v2.user.application.port.in.dto.VerifyEmailDto;
import com.panda.back.v2.user.application.port.out.ReadUserPort;
import com.panda.back.v2.user.application.port.out.VerifyUserPort;
import com.panda.back.v2.user.domain.Verification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyUserService implements VerifyUserUseCase {
  private final RandomHolder randomHolder;
  private final MailSender mailSender;
  private final ReadUserPort readUserPort;
  private final VerifyUserPort verifyUserPort;
  @Override
  public SendVerificationCodeDto sendEmail(SendVerificationCodeDto sendVerificationCodeDto) {
    if (readUserPort.existsByEmail(sendVerificationCodeDto.getEmail())) {
      throw new AlreadyExistsUserException();
    }
    Verification verification = Verification.from(sendVerificationCodeDto.getEmail(), randomHolder);

    mailSender.send(verification.getEmail(), verification.getVerificationCode());
    verifyUserPort.save(verification);
    verification.onWaiting();

    sendVerificationCodeDto.setSendSuccess(true);
    return sendVerificationCodeDto;
  }

  @Override
  public VerifyEmailDto verify(VerifyEmailDto verifyEmailDto) {
    Verification verification = verifyUserPort.findByEmail(verifyEmailDto.getEmail())
        .orElseThrow(VerifyUserException::new);
    verification.verify(verifyEmailDto.getVerificationCode());
    
    verifyUserPort.delete(verification);

    return VerifyEmailDto.builder()
        .email(verifyEmailDto.getEmail())
        .verificationCode(verification.getVerificationCode())
        .isVerify(true)
        .build();
  }
}
