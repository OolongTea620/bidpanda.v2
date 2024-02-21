package com.panda.back.v2.user.adapter.out.redis.repository;

import com.panda.back.v2.user.adapter.out.redis.entity.VerificationEntity;
import java.util.Optional;

public interface VerificationRedisRepository {

  Optional<VerificationEntity> findByEmail(String email);

  VerificationEntity save(VerificationEntity verificationEntity, long ttl);
  void delete(VerificationEntity verificationEntity);
}
