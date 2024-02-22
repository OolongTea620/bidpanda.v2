package com.panda.back.user.application;

import com.panda.back.user.application.port.in.CreateUserUseCase;
import com.panda.back.user.application.port.in.dto.CreateUserDto;
import com.panda.back.user.application.port.out.CreateUserPort;
import com.panda.back.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
  private final CreateUserPort createUserPort;
  @Override
  public CreateUserDto create(CreateUserDto createUserDto) {
    User user = User.from(createUserDto);
    user = createUserPort.save(user);

    return CreateUserDto.from(user);
  }
}
