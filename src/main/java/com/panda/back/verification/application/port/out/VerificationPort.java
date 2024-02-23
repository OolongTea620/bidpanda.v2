package com.panda.back.verification.application.port.out;

import com.panda.back.verification.domain.Verification;
import com.panda.back.verification.domain.VerificationStatus;
import java.util.Optional;

public interface VerificationPort {
  Verification save(Verification verification);
  Optional<Verification> findByEmailAndStatus(String email, VerificationStatus status);
  void delete(Verification verification);
}
