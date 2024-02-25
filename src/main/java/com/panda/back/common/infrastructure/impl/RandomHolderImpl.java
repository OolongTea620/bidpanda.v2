package com.panda.back.common.infrastructure.impl;


import com.panda.back.common.infrastructure.RandomHolder;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomHolderImpl implements RandomHolder {
  private final Random random;
  public RandomHolderImpl() {
    this.random = new Random();
  }
  @Override
  public String random() {
    return String.valueOf(random.nextInt(888888) + 111111);
  }

  @Override
  public int getSaltRound() {
    return random.nextInt(4,11);
  }
}
