package com.panda.back.user.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.panda.back.v2.common.exception.user.VerifyUserException;
import com.panda.back.mock.FakeRandomHolder;
import org.junit.jupiter.api.Test;

class VerificationTest {

  @Test
  void Email과RandomHolder로_생성하고_GETNERATE상태를_가진다() {
    String email = "test1234@test.com";
    Verification verification = Verification
        .from(email, new FakeRandomHolder("123456"));

    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getEmail()).isEqualTo("test1234@test.com");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.GENERATE);
  }

  @Test
  void Verification_status를_GENERATE에서_WATING으로_변경한다() {
    //given
    Verification verification = Verification
        .from("test1234@gmail.com", new FakeRandomHolder("123456"));

    //then
    verification.onWaiting();

    //when
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.WAITING);
  }

  @Test
  void WAITING상태에서_인증_성공하면_VERIFIED상태가_된다() {
    //given
    Verification verification = Verification.builder()
        .email("test1234@gmail.com")
        .verificationCode(new FakeRandomHolder("123456").random())
        .status(VerificationStatus.WAITING)
        .build();
    String correctCode = "123456";
    //when
    verification.verify(correctCode);
    //then
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.VERIFIED);
  }

  @Test
  void WAITING상태에서_인증_실패하면_예외가_발생한다() {
    //given
    Verification verification = Verification.builder()
        .email("test1234@gmail.com")
        .verificationCode(new FakeRandomHolder("123456").random())
        .status(VerificationStatus.WAITING)
        .build();
    String incorrectCode = "7654321";
    //when
    //then
    assertThatThrownBy(() -> verification.verify(incorrectCode))
        .isInstanceOf(VerifyUserException.class);
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.WAITING);
  }

  @Test
  void GENERATE_상태에서_인증을하면_예외가발생한다() {
    //given
    Verification verification = Verification.builder()
        .email("test1234@gmail.com")
        .verificationCode(new FakeRandomHolder("123456").random())
        .status(VerificationStatus.GENERATE)
        .build();

    String correctCode = "123456";
    //when
    //then
    assertThatThrownBy(() -> verification.verify(correctCode))
        .isInstanceOf(VerifyUserException.class);
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.GENERATE);
  }

  @Test
  void VERIFIED_상태에서_인증을하면_예외가발생한다() {
    //given
    Verification verification = Verification.builder()
        .email("test1234@gmail.com")
        .verificationCode(new FakeRandomHolder("123456").random())
        .status(VerificationStatus.VERIFIED)
        .build();

    String correctCode = "123456";
    //when
    //then
    assertThatThrownBy(() -> verification.verify(correctCode))
        .isInstanceOf(VerifyUserException.class);
    assertThat(verification.getEmail()).isEqualTo("test1234@gmail.com");
    assertThat(verification.getVerificationCode()).isEqualTo("123456");
    assertThat(verification.getStatus()).isEqualTo(VerificationStatus.VERIFIED);
  }

}