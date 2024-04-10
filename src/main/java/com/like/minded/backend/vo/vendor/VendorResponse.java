/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.vendor;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VendorResponse {
    private Integer status;
    private String message;
}
