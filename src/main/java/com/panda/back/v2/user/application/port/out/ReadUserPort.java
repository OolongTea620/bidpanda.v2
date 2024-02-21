package com.panda.back.v2.user.application.port.out;


import com.panda.back.v2.user.domain.User;
import java.util.Optional;

public interface ReadUserPort {

  Optional<User> findById(Long id);

  Optional<User> findByEmail(String email);


  boolean existsByEmail(String email);
}
