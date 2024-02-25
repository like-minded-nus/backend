package com.like.minded.backend.dto.match;

import com.like.minded.backend.domain.match.Match;
import com.like.minded.backend.domain.profile.Profile;
import com.like.minded.backend.dto.profile.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseBodyDto {
    private Integer matchId;
    private Integer profileId_1;
    private Integer profileId_2;
    private Boolean like_1;
    private Boolean like_2;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;
    private ProfileDto profile;

    public MatchResponseBodyDto (Match match, ProfileDto profileDto) {
        this.matchId = match.getMatchId();
        this.profileId_1 = match.getProfileId_1();
        this.profileId_2 = match.getProfileId_2();
        this.like_1 = match.getLike_1();
        this.like_2 = match.getLike_2();
        this.createdBy = match.getCreatedBy();
        this.createdDate = match.getCreatedDate();
        this.updatedBy = match.getUpdatedBy();
        this.updatedDate = match.getUpdatedDate();
        this.profile = profileDto;
    }
}

