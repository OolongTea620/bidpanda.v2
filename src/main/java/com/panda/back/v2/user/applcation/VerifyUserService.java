package com.panda.back.v2.user.applcation;

import com.panda.back.v2.common.exception.user.AlreadyExistsUserException;
import com.panda.back.v2.common.infrastructure.MailSender;
import com.panda.back.v2.common.infrastructure.RandomHolder;
import com.panda.back.v2.user.applcation.port.in.VerifyUserUseCase;
import com.panda.back.v2.user.applcation.port.in.dto.SendVerificationCodeDto;
import com.panda.back.v2.user.applcation.port.in.dto.VerifyEmailDto;
import com.panda.back.v2.user.applcation.port.out.ReadUserPort;
import com.panda.back.v2.user.domain.User;
import com.panda.back.v2.user.domain.Verification;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyUserService implements VerifyUserUseCase {
  private final RandomHolder randomHolder;
  private final MailSender mailSender;
  private final ReadUserPort readUserPort;
  @Override
  public SendVerificationCodeDto sendEmail(SendVerificationCodeDto sendVerificationCodeDto) {
    if (readUserPort.existsByEmail(sendVerificationCodeDto.getEmail())) {
      throw new AlreadyExistsUserException();
    }

    Verification verification = Verification.from(sendVerificationCodeDto.getEmail(), randomHolder);
    mailSender.send(verification.getEmail(), verification.getVerificationCode());
    verification.setWaiting();

    // Redis에 캐싱 -> 10분 유효

    return null;
  }

  @Override
  public VerifyEmailDto verify(VerifyEmailDto verifyEmailDto) {
    return null;
  }
}
