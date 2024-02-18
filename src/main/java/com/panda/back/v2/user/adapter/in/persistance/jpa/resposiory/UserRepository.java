package com.panda.back.v2.user.adapter.in.persistance.jpa.resposiory;

import com.panda.back.v2.user.adapter.in.persistance.jpa.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);
  boolean existsByEmail(String email);
}
