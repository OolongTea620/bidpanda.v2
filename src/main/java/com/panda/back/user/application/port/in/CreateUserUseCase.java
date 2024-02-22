package com.panda.back.user.application.port.in;

import com.panda.back.user.application.port.in.dto.CreateUserDto;

public interface CreateUserUseCase {
  CreateUserDto create(CreateUserDto createUserDto);
}
