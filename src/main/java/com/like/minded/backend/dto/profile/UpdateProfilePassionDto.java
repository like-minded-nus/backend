/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import com.like.minded.backend.domain.profile.ProfilePassion;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfilePassionDto {
    private Integer profileId;
    private List<ProfilePassion> profilePassionList;
}
