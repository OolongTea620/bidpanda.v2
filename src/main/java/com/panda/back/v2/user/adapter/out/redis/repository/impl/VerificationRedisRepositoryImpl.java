package com.panda.back.v2.user.adapter.out.redis.repository.impl;

import com.panda.back.v2.user.adapter.out.redis.entity.VerificationEntity;
import com.panda.back.v2.user.adapter.out.redis.repository.VerificationRedisRepository;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationRedisRepositoryImpl implements VerificationRedisRepository {
  private final StringRedisTemplate stringRedisTemplate;
  private final static long TTL_SECONDS = 600L;
  private final static String KEY_PREFIX = "verify:";
  @Override
  public Optional<VerificationEntity> findByEmail(String email) {
    String key = KEY_PREFIX + email;
    String value = stringRedisTemplate.opsForValue().get(key);
    if (Objects.isNull(value)) {
      return Optional.empty();
    }
    return Optional.of(VerificationEntity.from(email, value));
  }

  @Override
  public VerificationEntity save(VerificationEntity entity, long ttl) {
    String key = KEY_PREFIX + entity.getEmail();
    stringRedisTemplate.opsForValue().set(key, entity.getVerificationCode(),ttl, TimeUnit.SECONDS);
    return entity;
  }
  @Override
  public void delete(VerificationEntity verificationEntity) {
    String key = KEY_PREFIX + verificationEntity.getEmail();
    stringRedisTemplate.opsForValue().getAndDelete(key);
  }
}
