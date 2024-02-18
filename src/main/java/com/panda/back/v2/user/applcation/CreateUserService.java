package com.panda.back.v2.user.applcation;

import com.panda.back.v2.common.exception.user.AlreadyExistsUserException;
import com.panda.back.v2.user.applcation.port.in.CreateUserUseCase;
import com.panda.back.v2.user.applcation.port.in.dto.CreateUserDto;
import com.panda.back.v2.user.applcation.port.out.CreateUserPort;
import com.panda.back.v2.user.applcation.port.out.ReadUserPort;
import com.panda.back.v2.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

  private final CreateUserPort createUserPort;
  private final ReadUserPort readUserPort;

  @Override
  @Transactional
  public User create(CreateUserDto createUserDto) {
    if (readUserPort.existsByEmail(createUserDto.getEmail())) {
      throw new AlreadyExistsUserException();
    }
    User user = User.from(createUserDto);
    user = createUserPort.save(user);
    return user;
  }
}
