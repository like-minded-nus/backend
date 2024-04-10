/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePassionMatchListDto {
    private Integer profileId;
    private List<ProfilePassionMatchDto> matchList;
}
