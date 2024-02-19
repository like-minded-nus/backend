package com.like.minded.backend.vo.profile;

import lombok.Builder;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class GetProfileResponse {
    private Integer status;
    private String message;
    private Integer profileId;
    private Integer userId;
    private String displayName;
    private String gender;
    private LocalDate birthdate;
    private String bio;
    private List <String> profilePassionList;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
}
