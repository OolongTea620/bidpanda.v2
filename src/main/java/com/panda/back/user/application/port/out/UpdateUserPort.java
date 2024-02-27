package com.panda.back.user.application.port.out;

import com.panda.back.user.domain.User;

public interface UpdateUserPort {
  User update(User user);
}
