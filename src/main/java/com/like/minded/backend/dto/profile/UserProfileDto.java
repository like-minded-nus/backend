package com.like.minded.backend.dto.profile;

import com.like.minded.backend.domain.profile.ProfilePassion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private Integer profileId;
    private Integer userId;
    private String displayName;
    private String gender;
    private LocalDate birthdate;
    private List<Integer> profilePassionList;
}

