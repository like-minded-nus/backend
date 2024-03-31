package com.like.minded.backend.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserTest {

    @Test
    void testUserCreationAndProperties() {
        String username = "test";
        String password = "1234";
        String email = "test@test.com";
        UserRole userRole = mock(UserRole.class);

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .userRole(userRole)
                .build();

        assertNotNull(user, "User object should not be null");
        assertEquals(username, user.getUsername(), "Username should match the provided value");
        assertEquals(password, user.getPassword(), "Password should match the provided value");
        assertEquals(email, user.getEmail(), "Email should match the provided value");
        assertNotNull(user.getUserRole(), "User role should not be null");
    }
}
