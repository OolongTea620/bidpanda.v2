package com.panda.back.verification.adapter.port.in.web;

import com.panda.back.verification.adapter.port.in.web.dto.VerifyEmailReq;
import com.panda.back.verification.application.in.VerifyUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verify")
@RequiredArgsConstructor
public class VerificationRestController {
  private final VerifyUseCase verificationUseCase;
  @PostMapping("/user")
  public void verifyByEmail(@RequestBody @Valid VerifyEmailReq verifyEmailReq) {
  }
}
