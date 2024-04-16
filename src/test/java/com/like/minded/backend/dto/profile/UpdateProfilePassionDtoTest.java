/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.domain.profile.ProfilePassion;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class UpdateProfilePassionDtoTest {

    @Test
    void testEquals() {
        ProfilePassion passion1 = new ProfilePassion(1, 10, 100);
        ProfilePassion passion2 = new ProfilePassion(1, 10, 100);
        ProfilePassion passion3 = new ProfilePassion(2, 11, 101);

        List<ProfilePassion> list1 = Arrays.asList(passion1, passion2);
        List<ProfilePassion> list2 = Arrays.asList(passion1, passion2);
        List<ProfilePassion> list3 = Arrays.asList(passion3);

        UpdateProfilePassionDto dto1 = new UpdateProfilePassionDto(1, list1);
        UpdateProfilePassionDto dto2 = new UpdateProfilePassionDto(1, list2);
        UpdateProfilePassionDto dto3 = new UpdateProfilePassionDto(2, list3);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        ProfilePassion passion1 = new ProfilePassion(1, 10, 100);
        ProfilePassion passion2 = new ProfilePassion(1, 10, 100);

        List<ProfilePassion> list1 = Arrays.asList(passion1, passion2);
        List<ProfilePassion> list2 = Arrays.asList(passion1, passion2);

        UpdateProfilePassionDto dto1 = new UpdateProfilePassionDto(1, list1);
        UpdateProfilePassionDto dto2 = new UpdateProfilePassionDto(1, list2);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
