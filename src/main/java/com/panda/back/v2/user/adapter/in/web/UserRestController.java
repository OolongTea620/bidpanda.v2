package com.panda.back.v2.user.adapter.in.web;

import com.panda.back.v2.user.adapter.in.web.dto.CreateUserReq;
import com.panda.back.v2.user.adapter.in.web.dto.CreateUserRes;
import com.panda.back.v2.user.adapter.in.web.dto.SendVerificationCodeRes;
import com.panda.back.v2.user.adapter.in.web.dto.VerifyEmailReq;
import com.panda.back.v2.user.adapter.in.web.dto.VerifyEmailRes;
import com.panda.back.v2.user.applcation.port.in.CreateUserUseCase;
import com.panda.back.v2.user.applcation.port.in.ReadUserUseCase;
import com.panda.back.v2.user.applcation.port.in.VerifyUserUseCase;
import com.panda.back.v2.user.applcation.port.in.dto.SendVerificationCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
  private final CreateUserUseCase createUserUseCase;
  private final ReadUserUseCase readUserUseCase;
  private final VerifyUserUseCase verifyUserUseCase;
  // Exists email
  @GetMapping("")
  public ResponseEntity<?> existsEmail(@RequestParam String email) {
    return ResponseEntity
        .ok()
        .body(readUserUseCase.existsEmail(email));
  }

  @PostMapping("/verification")
  public ResponseEntity<VerifyEmailRes> verifyEmail(@RequestBody VerifyEmailReq verifyEmailReq) {
    VerifyEmailRes result = VerifyEmailRes
        .from(verifyUserUseCase.verify(verifyEmailReq.toDto()));
    return ResponseEntity
        .ok()
        .body(result);
  }

  @GetMapping("/verification/{email}")
  public ResponseEntity<SendVerificationCodeRes> sendEmail (@PathVariable String email) {
    SendVerificationCodeRes result = SendVerificationCodeRes
        .from(verifyUserUseCase.sendEmail(new SendVerificationCodeDto(email)));

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(result);
  }


  @PostMapping("")
  public ResponseEntity<CreateUserRes> create(@RequestBody CreateUserReq createUserReq) {
    CreateUserRes result = CreateUserRes
        .from(createUserUseCase.create(createUserReq.toDto()));
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping("/my")
  public ResponseEntity<?> getMyInfo() {
    return null;
  }
}
