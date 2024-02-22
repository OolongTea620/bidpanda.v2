package com.panda.back.common.exception;

import com.panda.back.common.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<BaseResponse<?>> handleException(CustomException ex) {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(BaseResponse.error(ex.getErrorCode().getMessage()));
    }
}
