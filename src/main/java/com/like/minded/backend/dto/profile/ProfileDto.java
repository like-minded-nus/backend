/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.profile;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
