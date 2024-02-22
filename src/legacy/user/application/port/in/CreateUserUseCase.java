package com.panda.back.v2.user.application.port.in;

import com.panda.back.v2.user.application.port.in.dto.CreateUserDto;
import com.panda.back.v2.user.domain.User;

public interface CreateUserUseCase {

  User create(CreateUserDto createUserDto);
}
