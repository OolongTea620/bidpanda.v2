package com.panda.back.user.adapter.port.out.persistance.repository;

import com.panda.back.user.adapter.port.out.persistance.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
  Optional<UserEntity> findUserEntityByEmail(String email);
}
