package com.panda.back.verification.application;

import com.panda.back.common.exception.user.AlreadyExistsUserException;
import com.panda.back.common.exception.verification.VerificationNotFoundException;
import com.panda.back.common.infrastructure.MailSender;
import com.panda.back.common.infrastructure.RandomHolder;
import com.panda.back.user.application.port.out.ReadUserPort;
import com.panda.back.verification.application.port.in.VerifyUseCase;
import com.panda.back.verification.application.port.in.dto.VerifyEmailDto;
import com.panda.back.verification.application.port.out.VerificationPort;
import com.panda.back.verification.domain.Verification;
import com.panda.back.verification.domain.VerificationStatus;
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
    verificationPort.save(verification);
  }

  @Override
  public VerifyEmailDto verify(VerifyEmailDto verifyEmailDto) {
    Verification verification = verificationPort.findByEmailAndStatus(verifyEmailDto.getEmail(), VerificationStatus.Send)
        .orElseThrow(VerificationNotFoundException::new);
    
    verification.verify(verification.getVerificationCode());
    // TODO : Send 삭제 레디스 생각해보기
    verificationPort.save(verification);
    return VerifyEmailDto.builder()
        .email(verifyEmailDto.getEmail())
        .verificationCode(verifyEmailDto.getVerificationCode())
        .isVerified(true)
        .build();
  }
}
