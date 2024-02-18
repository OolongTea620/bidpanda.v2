package com.panda.back.v2.user.adapter.in.persistance;

import com.panda.back.v2.user.adapter.in.persistance.jpa.entity.UserEntity;
import com.panda.back.v2.user.adapter.in.persistance.jpa.resposiory.UserRepository;
import com.panda.back.v2.user.applcation.port.out.CreateUserPort;
import com.panda.back.v2.user.applcation.port.out.DeleteUserPort;
import com.panda.back.v2.user.applcation.port.out.ReadUserPort;
import com.panda.back.v2.user.applcation.port.out.UpdateUserPort;
import com.panda.back.v2.user.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort, ReadUserPort, UpdateUserPort,
    DeleteUserPort {

  private final UserRepository userRepository;

  @Override
  public Optional<User> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email).map(UserEntity::toModel);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public User save(User user) {
    return userRepository.save(UserEntity.fromModel(user)).toModel();
  }

  @Override
  public User update(User user) {
    return user;
  }
}
