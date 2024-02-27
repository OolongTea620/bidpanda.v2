package com.panda.back.user.adapter.port.out.persistance;

import com.panda.back.user.adapter.port.out.persistance.entity.UserEntity;
import com.panda.back.user.adapter.port.out.persistance.repository.UserJpaRepository;
import com.panda.back.user.application.port.out.CreateUserPort;
import com.panda.back.user.application.port.out.ReadUserPort;
import com.panda.back.user.application.port.out.UpdateUserPort;
import com.panda.back.user.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements CreateUserPort, ReadUserPort, UpdateUserPort {
  private final UserJpaRepository userJpaRepository;
  @Override
  public Optional<User> findByEmail(String email) {
    return userJpaRepository.findUserEntityByEmail(email).map(UserEntity::toModel);
  }

  @Override
  public User save(User user) {
    return userJpaRepository.save(UserEntity.fromModel(user)).toModel();
  }

  @Override
  public User update(User user) {
    return userJpaRepository.save(UserEntity.fromModel(user)).toModel();
  }
}
