/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class UpdateUserProfileDtoTest {

    @Test
    void testEquals() {
        UpdateUserProfileDto dto1 =
                new UpdateUserProfileDto(
                        1,
                        "John Doe",
                        "Bio",
                        Arrays.asList(1, 2),
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");
        UpdateUserProfileDto dto2 =
                new UpdateUserProfileDto(
                        1,
                        "John Doe",
                        "Bio",
                        Arrays.asList(1, 2),
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");
        UpdateUserProfileDto dto3 =
                new UpdateUserProfileDto(
                        2,
                        "Jane Doe",
                        "Bio2",
                        Arrays.asList(3),
                        "img1a",
                        "img2a",
                        "img3a",
                        "img4a",
                        "img5a",
                        "img6a");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        UpdateUserProfileDto dto1 =
                new UpdateUserProfileDto(
                        1,
                        "John Doe",
                        "Bio",
                        Arrays.asList(1, 2),
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");
        UpdateUserProfileDto dto2 =
                new UpdateUserProfileDto(
                        1,
                        "John Doe",
                        "Bio",
                        Arrays.asList(1, 2),
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
