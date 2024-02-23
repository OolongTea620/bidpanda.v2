package com.panda.back.verification.adapter.port.out.redis.repository;

import com.panda.back.verification.domain.Verification;
import java.util.Optional;

public interface VerificationRedisRepository {

  Optional<Verification> findByEmail(String email);

  Verification save(Verification verification);

  Verification update(Verification verification);

  void delete(Verification verification);
}
