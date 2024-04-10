/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePassionMatchDto {
    private Integer profileId;
    private Integer similarityScore;
}
