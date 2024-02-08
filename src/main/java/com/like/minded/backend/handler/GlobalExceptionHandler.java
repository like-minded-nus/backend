package com.like.minded.backend.handler;

import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.LoginException;
import com.like.minded.backend.exception.RegistrationException;
import com.like.minded.backend.vo.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<UserResponse> handleRegistrationException(RegistrationException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(
                UserResponse.builder()
                        .status(400)
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(DatabaseTransactionException.class)
    public ResponseEntity<UserResponse> handleUserRegistrationDatabaseTxException(DatabaseTransactionException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(
                UserResponse.builder()
                        .status(500)
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<UserResponse> handleUserLoginException(LoginException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(
                UserResponse.builder()
                        .status(401)
                        .message(ex.getMessage())
                        .build()
        );
    }
}
