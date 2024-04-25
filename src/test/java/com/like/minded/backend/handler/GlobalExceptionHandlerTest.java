/* LikeMinded (C)2024 */
package com.like.minded.backend.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.exception.LoginException;
import com.like.minded.backend.exception.RegistrationException;
import com.like.minded.backend.vo.user.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void handleRegistrationException() {
        RegistrationException ex = new RegistrationException("Registration failed");
        ResponseEntity<UserResponse> response =
                globalExceptionHandler.handleRegistrationException(ex);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Registration failed", response.getBody().getMessage());
    }

    @Test
    void handleUserRegistrationDatabaseTxException() {
        DatabaseTransactionException ex = new DatabaseTransactionException("Database error");
        ResponseEntity<UserResponse> response =
                globalExceptionHandler.handleUserRegistrationDatabaseTxException(ex);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Database error", response.getBody().getMessage());
    }

    @Test
    void handleUserLoginException() {
        LoginException ex = new LoginException("Unauthorized access");
        ResponseEntity<UserResponse> response = globalExceptionHandler.handleUserLoginException(ex);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(401, response.getBody().getStatus());
        assertEquals("Unauthorized access", response.getBody().getMessage());
    }
}
