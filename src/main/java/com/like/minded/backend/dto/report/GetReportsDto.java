/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReportsDto {
    private Integer reportId;
    private Integer userId;
    private String username;
    private Integer reportedBy;
    private String reportedReason;
}
