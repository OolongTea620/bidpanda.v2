package com.panda.back.verification.adapter.port.out.redis.repository.impl;

import com.panda.back.verification.adapter.port.out.redis.repository.VerificationRedisRepository;
import com.panda.back.verification.domain.Verification;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class VerificationRedisRepositoryImpl implements VerificationRedisRepository {
  @Override
  public Optional<Verification> findByEmail(String email) {
    return Optional.empty();
  }

  @Override
  public Verification save(Verification verification) {
    return null;
  }

  @Override
  public Verification update(Verification verification) {
    return null;
  }

  @Override
  public void delete(Verification verification) {

  }
}
