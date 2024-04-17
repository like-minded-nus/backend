/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ProfileDtoTest {

    @Test
    void testEquals() {
        ProfileDto dto1 =
                new ProfileDto(
                        1,
                        10,
                        "John Doe",
                        "Male",
                        LocalDate.of(1990, 1, 1),
                        "Bio",
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");
        ProfileDto dto2 =
                new ProfileDto(
                        1,
                        10,
                        "John Doe",
                        "Male",
                        LocalDate.of(1990, 1, 1),
                        "Bio",
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");
        ProfileDto dto3 =
                new ProfileDto(
                        2,
                        11,
                        "Jane Doe",
                        "Female",
                        LocalDate.of(1991, 2, 2),
                        "Bio2",
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
        ProfileDto dto1 =
                new ProfileDto(
                        1,
                        10,
                        "John Doe",
                        "Male",
                        LocalDate.of(1990, 1, 1),
                        "Bio",
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");
        ProfileDto dto2 =
                new ProfileDto(
                        1,
                        10,
                        "John Doe",
                        "Male",
                        LocalDate.of(1990, 1, 1),
                        "Bio",
                        "img1",
                        "img2",
                        "img3",
                        "img4",
                        "img5",
                        "img6");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
