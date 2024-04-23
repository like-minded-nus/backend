/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserResponseTest {

    @Test
    void testEquals() {
        UserResponse response1 = UserResponse.builder().status(200).message("Success").build();

        UserResponse response2 = UserResponse.builder().status(200).message("Success").build();

        UserResponse response3 = UserResponse.builder().status(404).message("Not Found").build();

        assertEquals(response1, response2);
        assertEquals(response2, response1);
        assertNotEquals(response1, response3);
        assertNotEquals(null, response2);
        assertNotEquals(response2, new Object());
    }

    @Test
    void testHashCode() {
        UserResponse response1 = UserResponse.builder().status(200).message("Success").build();

        UserResponse response2 = UserResponse.builder().status(200).message("Success").build();

        assertEquals(response1.hashCode(), response2.hashCode());
    }
}
