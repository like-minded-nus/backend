/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.passion;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.domain.passion.Passion;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PassionResponseTest {

    @Test
    void testEquals() {
        Passion passion1 = Passion.builder().passionId(1).passionName("Dancing").build();
        Passion passion2 = Passion.builder().passionId(1).passionName("Dancing").build();
        Passion passion3 = Passion.builder().passionId(2).passionName("Cooking").build();

        List<Passion> list1 = Arrays.asList(passion1, passion2);
        List<Passion> list2 = Arrays.asList(passion1, passion2);
        List<Passion> list3 = Arrays.asList(passion3);

        PassionResponse response1 = new PassionResponse(list1);
        PassionResponse response2 = new PassionResponse(list2);
        PassionResponse response3 = new PassionResponse(list3);

        assertEquals(response1, response2);
        assertEquals(response2, response1);
        assertNotEquals(response1, response3);
        assertNotEquals(null, response2);
        assertNotEquals(response2, new Object());
    }

    @Test
    void testHashCode() {
        Passion passion1 = Passion.builder().passionId(1).passionName("Dancing").build();
        Passion passion2 = Passion.builder().passionId(1).passionName("Dancing").build();

        List<Passion> list1 = Arrays.asList(passion1, passion2);
        List<Passion> list2 = Arrays.asList(passion1, passion2);

        PassionResponse response1 = new PassionResponse(list1);
        PassionResponse response2 = new PassionResponse(list2);

        assertEquals(response1.hashCode(), response2.hashCode());
    }
}
