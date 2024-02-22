package com.panda.back.user.adapter.port.in.web;

import com.panda.back.user.adapter.port.in.web.dto.CreateUserReq;
import com.panda.back.user.adapter.port.in.web.dto.CreateUserRes;
import com.panda.back.user.application.port.in.CreateUserUseCase;
import com.panda.back.user.application.port.in.ReadUserUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

  private final ReadUserUseCase readUserUseCase;
  private final CreateUserUseCase createUserUseCase;


  @GetMapping("/user")
  public ResponseEntity<Boolean> existsEmail(@RequestParam @Valid @Email String email) {
    return ResponseEntity
        .ok()
        .body(readUserUseCase.existsUser(email));
  }

  @PostMapping("")
  public ResponseEntity<CreateUserRes> create(@RequestBody @Valid CreateUserReq createUserReq) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CreateUserRes
            .from(createUserUseCase.create(createUserReq.toDto())));
  }

  @GetMapping("/my")
  public ResponseEntity<?> getMyInfo() {
    return null;
  }

}
