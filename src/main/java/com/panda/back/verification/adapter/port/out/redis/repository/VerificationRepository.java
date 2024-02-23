package com.panda.back.verification.adapter.port.out.redis.repository;

import java.util.Optional;

public interface VerificationRepository {

  Optional<String> findByKey(String key);
  void saveWithTTL(String key, String value, int ttl);

  void delete(String key);

}
