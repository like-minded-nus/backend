/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.vendor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class VendorResponse {
    private Integer status;
    private String message;
}
