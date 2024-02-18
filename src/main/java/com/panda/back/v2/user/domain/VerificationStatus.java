package com.panda.back.v2.user.domain;

public enum VerificationStatus {
  /**
   * GENERATE -> 인증코드랜덤 발급 상태. 이메일 전송 x 해당 이메일로 회원가입 불가 상태
   * <p>
   * WAITING -> 인증코드 이메일 전송 상태, 하지만 미인증이라서 해당 이메일 회원 가입 불가능
   * <p>
   * VERIFIED -> 사용자 인증 성공, 해당 이메일은 10분 내로 회원 가입 활성화
   */
  GENERATE, WAITING, VERIFIED
}
