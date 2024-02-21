package com.panda.back.v2.user.adapter.out.redis;

import com.panda.back.v2.user.adapter.out.redis.entity.VerificationEntity;
import com.panda.back.v2.user.adapter.out.redis.repository.VerificationRedisRepository;
import com.panda.back.v2.user.application.port.out.VerifyUserPort;
import com.panda.back.v2.user.domain.Verification;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VerificationRedisAdapter implements VerifyUserPort {
  private final VerificationRedisRepository verificationRedisRepository;
  private static final long TTL_SECONDS = 600L;
  @Override
  public void save(Verification verification) {
    verificationRedisRepository.save(VerificationEntity.from(verification), TTL_SECONDS);
  }

  @Override
  public Optional<Verification> findByEmail(String email) {
    return verificationRedisRepository.findByEmail(email).map(VerificationEntity::toModel);
  }

  @Override
  public void delete(Verification verification) {
    verificationRedisRepository.delete(VerificationEntity.from(verification));
  }
}
