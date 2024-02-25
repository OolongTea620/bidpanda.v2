package com.panda.back.user.application;

import com.panda.back.common.exception.verification.VerificationNotFoundException;
import com.panda.back.common.infrastructure.HashingHolder;
import com.panda.back.common.infrastructure.RandomHolder;
import com.panda.back.user.application.port.in.CreateUserUseCase;
import com.panda.back.user.application.port.in.dto.CreateUserDto;
import com.panda.back.user.application.port.out.CreateUserPort;
import com.panda.back.user.domain.User;
import com.panda.back.verification.application.port.out.VerificationPort;
import com.panda.back.verification.domain.Verification;
import com.panda.back.verification.domain.VerificationStatus;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
  private final CreateUserPort createUserPort;
  private final VerificationPort verificationPort;
  private final HashingHolder hashingHolder;
  @Override
  public CreateUserDto create(CreateUserDto createUserDto) {
    Verification verification = verificationPort
        .findByEmailAndStatus(createUserDto.getEmail(), VerificationStatus.Done)
        .orElseThrow(VerificationNotFoundException::new);

    User user = User.from(createUserDto).bcryptPassword(hashingHolder);
    user = createUserPort.save(user);

    verificationPort.delete(verification);
    return CreateUserDto.from(user);
  }
}
