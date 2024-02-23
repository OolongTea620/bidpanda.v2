package com.panda.back.verification.application.port.out;

import com.panda.back.verification.domain.Verification;

public interface VerificationPort {
  Verification save(Verification verification);
  Verification findByEmail(String email);

  Verification update(Verification verification);
  void delete(Verification verification);
}
