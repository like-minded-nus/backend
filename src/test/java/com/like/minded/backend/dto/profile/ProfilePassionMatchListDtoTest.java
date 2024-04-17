/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProfilePassionMatchListDtoTest {

    @Test
    void testEquals() {
        ProfilePassionMatchDto match1 = new ProfilePassionMatchDto(10, 80);
        ProfilePassionMatchDto match2 = new ProfilePassionMatchDto(10, 80);
        ProfilePassionMatchDto match3 = new ProfilePassionMatchDto(20, 70);
        List<ProfilePassionMatchDto> list1 = Arrays.asList(match1, match2);
        List<ProfilePassionMatchDto> list2 = Arrays.asList(match1, match2);
        List<ProfilePassionMatchDto> list3 = Arrays.asList(match3);

        ProfilePassionMatchListDto dto1 = new ProfilePassionMatchListDto(1, list1);
        ProfilePassionMatchListDto dto2 = new ProfilePassionMatchListDto(1, list2);
        ProfilePassionMatchListDto dto3 = new ProfilePassionMatchListDto(2, list3);

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        ProfilePassionMatchDto match1 = new ProfilePassionMatchDto(10, 80);
        ProfilePassionMatchDto match2 = new ProfilePassionMatchDto(10, 80);
        List<ProfilePassionMatchDto> list1 = Arrays.asList(match1, match2);
        List<ProfilePassionMatchDto> list2 = Arrays.asList(match1, match2);

        ProfilePassionMatchListDto dto1 = new ProfilePassionMatchListDto(1, list1);
        ProfilePassionMatchListDto dto2 = new ProfilePassionMatchListDto(1, list2);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
