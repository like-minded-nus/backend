package com.like.minded.backend.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Integer profileId;
    private Integer userId;
    private String displayName;
    private String gender;
    private LocalDate birthdate;
    private String bio;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
}
