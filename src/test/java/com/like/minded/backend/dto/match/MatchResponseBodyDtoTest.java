/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.match;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.dto.profile.ProfileDto;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class MatchResponseBodyDtoTest {

    @Test
    void testEquals() {
        LocalDateTime now = LocalDateTime.now();
        ProfileDto profileDto1 =
                new ProfileDto(
                        1, 1, "John Doe", "Male", null, null, null, null, null, null, null, null);
        ProfileDto profileDto2 =
                new ProfileDto(
                        1, 1, "John Doe", "Male", null, null, null, null, null, null, null, null);

        MatchResponseBodyDto dto1 =
                new MatchResponseBodyDto(
                        1, 100, 200, true, false, "Admin", now, "Admin", now, profileDto1);
        MatchResponseBodyDto dto2 =
                new MatchResponseBodyDto(
                        1, 100, 200, true, false, "Admin", now, "Admin", now, profileDto2);
        MatchResponseBodyDto dto3 =
                new MatchResponseBodyDto(
                        2, 300, 400, false, true, "User", now, "User", now, profileDto2);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        LocalDateTime now = LocalDateTime.now();
        ProfileDto profileDto =
                new ProfileDto(
                        1, 1, "John Doe", "Male", null, null, null, null, null, null, null, null);

        MatchResponseBodyDto dto1 =
                new MatchResponseBodyDto(
                        1, 100, 200, true, false, "Admin", now, "Admin", now, profileDto);
        MatchResponseBodyDto dto2 =
                new MatchResponseBodyDto(
                        1, 100, 200, true, false, "Admin", now, "Admin", now, profileDto);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
