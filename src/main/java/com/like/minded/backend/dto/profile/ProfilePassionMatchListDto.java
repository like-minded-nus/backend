package com.like.minded.backend.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePassionMatchListDto {
    private Integer profileId;
    private List<ProfilePassionMatchDto> matchList;
}
