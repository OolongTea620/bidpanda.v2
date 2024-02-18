package com.panda.back.v2.user.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.panda.back.v2.mock.FakeRandomHolder;
import org.junit.jupiter.api.Test;

class VerificationTest {

  @Test
  void Email과RandomHolder로_생성할_수_있다() {
    String email = "test1234@test.com";
    Verification verification = Verification.from(email, new FakeRandomHolder("123456"));

    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getEmail()).isEqualTo("test1234@test.com");
    assertThat(verification.getVerificationCode()).isEqualTo(VerificationStatus.GENERATE);
  }

}