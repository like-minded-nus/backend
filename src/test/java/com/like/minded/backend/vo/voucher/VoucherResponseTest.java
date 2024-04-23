/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.voucher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VoucherResponseTest {

    @Test
    void testEquals() {
        VoucherResponse response1 =
                VoucherResponse.builder().status(200).message("Success").build();

        VoucherResponse response2 =
                VoucherResponse.builder().status(200).message("Success").build();

        VoucherResponse response3 =
                VoucherResponse.builder().status(404).message("Not Found").build();

        assertEquals(response1, response2);
        assertEquals(response2, response1);
        assertNotEquals(response1, response3);
        assertNotEquals(null, response2);
        assertNotEquals(response2, new Object());
    }

    @Test
    void testHashCode() {
        VoucherResponse response1 =
                VoucherResponse.builder().status(200).message("Success").build();

        VoucherResponse response2 =
                VoucherResponse.builder().status(200).message("Success").build();

        assertEquals(response1.hashCode(), response2.hashCode());
    }
}
