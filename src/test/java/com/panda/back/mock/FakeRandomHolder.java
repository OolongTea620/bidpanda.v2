package com.panda.back.mock;

import com.panda.back.v2.common.infrastructure.RandomHolder;


public class FakeRandomHolder implements RandomHolder {
  private final String randomNum;
  public FakeRandomHolder(String randomNum) {
    this.randomNum = randomNum;
  }
  @Override
  public String random() {
    return this.randomNum;
  }

}
