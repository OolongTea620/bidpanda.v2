package com.panda.back.v2.user.applcation.port.in;

import com.panda.back.v2.user.applcation.port.in.dto.CreateUserDto;
import com.panda.back.v2.user.domain.User;

public interface CreateUserUseCase {

  User create(CreateUserDto createUserDto);
}
