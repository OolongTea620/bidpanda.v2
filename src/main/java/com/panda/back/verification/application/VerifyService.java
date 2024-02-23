package com.panda.back.verification.application;

import com.panda.back.common.exception.user.AlreadyExistsUserException;
import com.panda.back.common.infrastructure.MailSender;
import com.panda.back.common.infrastructure.RandomHolder;
import com.panda.back.user.application.port.out.ReadUserPort;
import com.panda.back.verification.application.port.in.VerifyUseCase;
import com.panda.back.verification.application.port.in.dto.VerifyEmailDto;
import com.panda.back.verification.application.port.out.VerificationPort;
import com.panda.back.verification.domain.Verification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyService implements VerifyUseCase {
  private final ReadUserPort readUserPort;
  private final VerificationPort verificationPort;
  private final RandomHolder randomHolder;
  private final MailSender mailSender;
  @Override
  public void sendEmail(String email) {
    if (readUserPort.findByEmail(email).isPresent()) {
      throw new AlreadyExistsUserException();
    }
    Verification verification = Verification.generate(email, randomHolder);
    mailSender.send(verification.getEmail(), verification.getVerificationCode());
    verification.afterSendEmail();
    System.out.println("========send Email Success===========");
    System.out.println("{ "+ verification.getVerificationCode() +" }");
    //verification = verificationPort.save(verification);
  }

  @Override
  public VerifyEmailDto verify(VerifyEmailDto verifyEmailDto) {
    // TODO Redis 키 로 값 찾기 ->
    // TODO Verify 진행하기 ->
    // TODO redis에 결과 저장하기 (5분)
    // TODO 결과 반환하기 ->
    return null;
  }
}
