package com.panda.back.v2.user.applcation.port.out;

import com.panda.back.v2.user.domain.Verification;

public interface VerifyUserPort {

  void saveVerification(Verification verification);

  void findByEmail(Verification verification);


}
