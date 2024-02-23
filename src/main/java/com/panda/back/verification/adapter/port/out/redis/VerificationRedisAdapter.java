package com.panda.back.verification.adapter.port.out.redis;


import com.panda.back.verification.adapter.port.out.redis.repository.VerificationRedisRepository;
import com.panda.back.verification.application.port.out.VerificationPort;
import com.panda.back.verification.domain.Verification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationRedisAdapter implements VerificationPort {

  private final VerificationRedisRepository verificationRedisRepository;
  @Override
  public Verification save(Verification verification) {
    return null;
  }

  @Override
  public Verification findByEmail(String email) {
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
