/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.report;

/* LikeMinded (C)2024 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReportedProfileDto {
    private Integer reportId;
    private Integer profileId;
    private Integer userId;
    private String displayName;
    private String reportedReason;
}
