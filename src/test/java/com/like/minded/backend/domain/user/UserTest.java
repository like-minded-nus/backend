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
                .isPremium(1)
                .build();

        assertNotNull(user, "User object should not be null");
        assertEquals(username, user.getUsername(), "Username should match the provided value");
        assertEquals(password, user.getPassword(), "Password should match the provided value");
        assertEquals(email, user.getEmail(), "Email should match the provided value");
        assertNotNull(user.getUserRole(), "User role should not be null");
    }

    @Test
    void equalsVerifiesAllFields() {
        UserRole userRole1 = new UserRole(1, "ADMIN");
        UserRole userRole2 = new UserRole(1, "ADMIN");
        UserRole userRoleDifferent = new UserRole(2, "USER");

        User user1 = User.builder()
                .userId(1)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .userRole(userRole1)
                .isPremium(1)
                .build();

        User user2 = User.builder()
                .userId(1)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .userRole(userRole2)
                .isPremium(1)
                .build();

        User userDifferent = User.builder()
                .userId(2)
                .username("jane_doe")
                .password("password1234")
                .email("jane@example.com")
                .userRole(userRoleDifferent)
                .isPremium(0)
                .build();

        assertEquals(user1, user2, "Users with the same field values should be considered equal.");
        assertNotEquals(user1, userDifferent, "Users with different field values should not be considered equal.");
    }

    @Test
    void hashCodeIsConsistentWithEquals() {
        UserRole userRole = new UserRole(1, "ADMIN");

        User user1 = User.builder()
                .userId(1)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .userRole(userRole)
                .isPremium(1)
                .build();

        User user2 = User.builder()
                .userId(1)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .userRole(userRole)
                .isPremium(1)
                .build();

        assertEquals(user1.hashCode(), user2.hashCode(), "Equal users must have the same hash code.");

        int userHashCode1 = user1.hashCode();
        assertEquals(userHashCode1, user1.hashCode(), "Hash code should remain consistent across invocations.");
    }

    @Test
    void differentUsersHaveDifferentHashCodes() {
        UserRole userRole1 = new UserRole(1, "ADMIN");
        UserRole userRole2 = new UserRole(2, "USER");

        User user1 = User.builder()
                .userId(1)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .userRole(userRole1)
                .isPremium(1)
                .build();

        User userDifferent = User.builder()
                .userId(2)
                .username("jane_doe")
                .password("password1234")
                .email("jane@example.com")
                .userRole(userRole2)
                .isPremium(2)
                .build();

        assertNotEquals(user1.hashCode(), userDifferent.hashCode(), "Different users ideally have different hash codes.");
    }
}
