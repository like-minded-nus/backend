/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private Integer id;
    private Integer userId;
    private Integer reportedBy;
    private String reportedReason;
}
