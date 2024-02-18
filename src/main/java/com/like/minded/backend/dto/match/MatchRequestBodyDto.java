package com.like.minded.backend.dto.match;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequestBodyDto {
    private Integer userProfileId;
    private Integer targetProfileId;
    private Boolean like;
}

