package com.panda.back.verification.adapter.port.out.redis.repository.impl;

import com.panda.back.verification.adapter.port.out.redis.repository.VerificationRepository;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationRepositoryImpl implements VerificationRepository {
  private final StringRedisTemplate stringRedisTemplate;
  @Override
  public Optional<String> findByKey(String key) {
    return Optional.ofNullable(stringRedisTemplate.opsForValue().get(key));
  }

  @Override
  public void saveWithTTL(String key, String value, int ttl) {
    stringRedisTemplate.opsForValue().set(key,value, ttl, TimeUnit.SECONDS);
  }

  @Override
  public void delete(String key) {
    stringRedisTemplate.delete(key);
  }
}
