package com.panda.back.v2.user.application.port.out;

import com.panda.back.v2.user.domain.User;

public interface CreateUserPort {

  User save(User user);
}
