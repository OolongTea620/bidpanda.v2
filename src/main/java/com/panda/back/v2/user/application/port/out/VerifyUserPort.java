package com.panda.back.v2.user.application.port.out;

import com.panda.back.v2.user.domain.Verification;
import java.util.Optional;

public interface VerifyUserPort {
  void save(Verification verification);
  Optional<Verification> findByEmail(String email);
  void delete(Verification verification);
}
