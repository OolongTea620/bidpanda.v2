package com.panda.back.verification.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.panda.back.common.exception.verification.VerifyUserException;
import com.panda.back.mock.FakeRandomHolder;
import org.junit.jupiter.api.Test;

class VerificationTest {
  @Test
  void Verification은_Email와_RandomHolder로_생성된다() {
    String email = "test1234@test.com";
    Verification verification = Verification.generate(email, new FakeRandomHolder("123456"));

    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getEmail()).isEqualTo("test1234@test.com");
    assertThat(verification.getStatus()).isNull();
  }

  @Test
  void afterSendEmail은_status를_Send으로_변경한다() {
    //given
    Verification verification = Verification
        .generate("test1234@gmail.com", new FakeRandomHolder("123456"));

    //then
    verification.successSendEmail();

    //when
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.Send);
  }


  @Test
  void Send상태에서_인증성공을_하면_Done상태가_된다() {
    //given
    Verification verification = Verification.builder()
        .email("test1234@gmail.com")
        .verificationCode(new FakeRandomHolder("123456").random())
        .status(VerificationStatus.Send)
        .build();

    //when
    String correctCode = "123456";
    verification.verify(correctCode);
    //then
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.Done);
  }

  @Test
  void Send상태에서_인증실패을_하면_예외가_발생된다() {
    //given
    Verification verification = Verification.builder()
        .email("test1234@gmail.com")
        .verificationCode(new FakeRandomHolder("123456").random())
        .status(VerificationStatus.Send)
        .build();

    //when
    String inCorrectCode = "7654321";
    //then
    assertThatThrownBy(() -> verification
        .verify(inCorrectCode)).isInstanceOf(VerifyUserException.class);
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.Send);
  }

  @Test
  void Done_상태에서_인증을하면_예외가_발생된다() {
    //given
    Verification verification = Verification.builder()
        .email("test1234@gmail.com")
        .verificationCode(new FakeRandomHolder("123456").random())
        .status(VerificationStatus.Done)
        .build();

    String correctCode = "123456";
    //when
    //then
    assertThatThrownBy(() -> verification.verify(correctCode))
        .isInstanceOf(VerifyUserException.class);
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.Done);
  }
}