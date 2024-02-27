package com.panda.back.user.application;

import com.panda.back.common.exception.user.NotFoundUserException;
import com.panda.back.common.infrastructure.HashingHolder;
import com.panda.back.user.application.port.in.UpdateUserUseCase;
import com.panda.back.user.application.port.in.dto.UpdatePasswordDto;
import com.panda.back.user.application.port.in.dto.UpdateUserDto;
import com.panda.back.user.application.port.out.ReadUserPort;
import com.panda.back.user.application.port.out.UpdateUserPort;
import com.panda.back.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {
  private final ReadUserPort readUserPort;
  private final UpdateUserPort updateUserPort;
  private final HashingHolder hashingHolder;
  @Override
  @Transactional
  public User update(UpdateUserDto updateUserDto) {
    User user = readUserPort.findByEmail(updateUserDto.getEmail())
        .orElseThrow(NotFoundUserException::new);
    user = user.modify(updateUserDto);
    user = updateUserPort.update(user);
    return user;
  }

  @Override
  @Transactional
  public User updatePassword(UpdatePasswordDto updatePasswordDto) {
    return null;
  }
}
