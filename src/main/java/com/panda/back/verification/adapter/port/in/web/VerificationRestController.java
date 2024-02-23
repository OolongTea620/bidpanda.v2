package com.panda.back.verification.adapter.port.in.web;

import com.panda.back.verification.adapter.port.in.web.dto.VerifyCodeReq;
import com.panda.back.verification.adapter.port.in.web.dto.VerifyEmailReq;
import com.panda.back.verification.adapter.port.in.web.dto.VerifyEmailRes;
import com.panda.back.verification.application.port.in.VerifyUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verify")
@RequiredArgsConstructor
public class VerificationRestController {
  private final VerifyUseCase verificationUseCase;
  @PostMapping("/code")
  public ResponseEntity<String> sendCodeByEmail(@RequestBody @Valid VerifyCodeReq verifyCodeReq) {
    verificationUseCase.sendEmail(verifyCodeReq.getEmail());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("ok");
  }

  @PutMapping("/email")
  public ResponseEntity<VerifyEmailRes> verify(@RequestBody @Valid VerifyEmailReq verifyEmailReq) {
    VerifyEmailRes result = VerifyEmailRes
        .from(verificationUseCase.verify(verifyEmailReq.toDto()));
    return ResponseEntity.ok()
        .body(result);
  }
}
