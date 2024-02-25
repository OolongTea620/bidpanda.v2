package com.panda.back.common.infrastructure.impl;

import com.panda.back.common.infrastructure.HashingHolder;
import com.panda.back.common.infrastructure.RandomHolder;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HashingHolderImpl implements HashingHolder {
  private final RandomHolder randomHolder;
  @Override
  public String hashPassword(String originPassword) {
    int round = randomHolder.getSaltRound();
    return BCrypt.hashpw(originPassword, BCrypt.gensalt(round));
  }
}
