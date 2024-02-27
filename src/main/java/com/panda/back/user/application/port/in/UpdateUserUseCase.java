package com.panda.back.user.application.port.in;

import com.panda.back.user.application.port.in.dto.UpdatePasswordDto;
import com.panda.back.user.application.port.in.dto.UpdateUserDto;
import com.panda.back.user.domain.User;

public interface UpdateUserUseCase {
  User update(UpdateUserDto updateUserDto);

  User updatePassword(UpdatePasswordDto updatePasswordDto);

}
