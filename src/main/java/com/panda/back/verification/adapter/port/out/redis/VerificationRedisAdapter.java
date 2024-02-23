package com.panda.back.verification.adapter.port.out.redis;


import com.panda.back.verification.adapter.port.out.redis.repository.VerificationRepository;
import com.panda.back.verification.application.port.out.VerificationPort;
import com.panda.back.verification.domain.Verification;
import com.panda.back.verification.domain.VerificationStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationRedisAdapter implements VerificationPort {
  private final VerificationRepository verificationRepository;
  private static final int TTL_SECOND = 300;
  private static final String VERIFY_PREFIX = "verify";
  @Override
  public Verification save(Verification verification) {
    String key = getKey(verification.getEmail(), verification.getStatus());
    verificationRepository.saveWithTTL(key, verification.getVerificationCode(),TTL_SECOND);
    return verification;
  }
  @Override
  public Optional<Verification> findByEmailAndStatus(String email, VerificationStatus status) {
    String key = getKey(email, status);
    Optional<String> result = verificationRepository.findByKey(key);

    return result.map(code-> Verification.builder()
        .email(email)
        .verificationCode(code)
        .status(status)
        .build());
  }
  @Override
  public void delete(Verification verification) {
    String key = getKey(verification.getEmail(), verification.getStatus());
    verificationRepository.delete(key);
  }

  private String getKey(String email, VerificationStatus status) {
    return String.format("%s:%s:%s",VERIFY_PREFIX ,email, status.toString());
  }
}
