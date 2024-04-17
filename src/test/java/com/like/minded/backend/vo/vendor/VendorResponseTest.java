/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.vendor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorResponseTest {

    @Test
    void testEquals() {
        VendorResponse response1 = VendorResponse.builder().status(200).message("Success").build();

        VendorResponse response2 = VendorResponse.builder().status(200).message("Success").build();

        VendorResponse response3 =
                VendorResponse.builder().status(404).message("Not Found").build();

        assertEquals(response1, response2);
        assertEquals(response2, response1);
        assertNotEquals(response1, response3);
        assertNotEquals(null, response2);
        assertNotEquals(response2, new Object());
    }

    @Test
    void testHashCode() {
        VendorResponse response1 = VendorResponse.builder().status(200).message("Success").build();

        VendorResponse response2 = VendorResponse.builder().status(200).message("Success").build();

        assertEquals(response1.hashCode(), response2.hashCode());
    }
}
