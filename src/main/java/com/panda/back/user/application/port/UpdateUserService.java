package com.panda.back.user.application.port;

import com.panda.back.user.application.port.in.UpdateUserUseCase;
import com.panda.back.user.application.port.in.dto.UpdatePasswordDto;
import com.panda.back.user.application.port.in.dto.UpdateUserDto;
import com.panda.back.user.application.port.out.UpdateUserPort;
import com.panda.back.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {
  private final UpdateUserPort updateUserPort;
  @Override
  public User update(UpdateUserDto updateUserDto) {
    return null;
  }

  @Override
  public User updatePassword(UpdatePasswordDto updatePasswordDto) {
    return null;
  }
}
