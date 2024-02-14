package com.like.minded.backend.vo.profile;

import lombok.Builder;
import lombok.Data;

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
    private List <String> profilePassionList;
}
