package com.panda.back.mock;

import com.panda.back.common.infrastructure.HashingHolder;

public class FakeHashingHolder implements HashingHolder {

  @Override
  public String hashPassword(String originPassword) {
    return "1234567890";
  }
}
