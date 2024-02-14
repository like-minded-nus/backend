package com.like.minded.backend.dto.profile;

import com.like.minded.backend.domain.profile.ProfilePassion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePassionDto {
    private List<ProfilePassion> profilePassionList;
}

