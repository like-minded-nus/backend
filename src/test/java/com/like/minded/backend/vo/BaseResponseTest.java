/* LikeMinded (C)2024 */
package com.like.minded.backend.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class BaseResponseTest {

    @Test
    void testEquals() {
        BaseResponse<String> response1 =
                new BaseResponse.Builder<String>()
                        .status(HttpStatus.OK.value())
                        .message("Success")
                        .payload("Data loaded successfully")
                        .build();

        BaseResponse<String> response2 =
                new BaseResponse.Builder<String>()
                        .status(HttpStatus.OK.value())
                        .message("Success")
                        .payload("Data loaded successfully")
                        .build();

        BaseResponse<String> response3 =
                new BaseResponse.Builder<String>()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Not Found")
                        .payload("Data not found")
                        .build();

        assertEquals(response1, response2);
        assertEquals(response2, response1);
        assertNotEquals(response1, response3);
        assertNotEquals(null, response2);
        assertNotEquals(response2, new Object());
    }

    @Test
    void testHashCode() {
        BaseResponse<String> response1 =
                new BaseResponse.Builder<String>()
                        .status(HttpStatus.OK.value())
                        .message("Success")
                        .payload("Data loaded successfully")
                        .build();

        BaseResponse<String> response2 =
                new BaseResponse.Builder<String>()
                        .status(HttpStatus.OK.value())
                        .message("Success")
                        .payload("Data loaded successfully")
                        .build();

        assertEquals(response1.hashCode(), response2.hashCode());
    }
}
