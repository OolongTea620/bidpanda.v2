package com.panda.back.user.application;

import com.panda.back.user.application.port.in.ReadUserUseCase;
import com.panda.back.user.application.port.out.ReadUserPort;
import com.panda.back.user.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadUserService implements ReadUserUseCase {

  private final ReadUserPort readUserPort;

  @Override
  public boolean existsUser(String email) {
    Optional<User> user = readUserPort.findByEmail(email);
    return user.isPresent();
  }
}
