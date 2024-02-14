package com.like.minded.backend.domain.profile;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence")
    @SequenceGenerator(name="profile_sequence", sequenceName = "profile_sequence", allocationSize = 1)
    @Column(name="PROFILE_ID")
    private Integer profileId;

    @NonNull
    @Column(name="USER_ID")
    private Integer userId;

    @NonNull
    @Column(name="DISPLAY_NAME")
    private String displayName;

    @NonNull
    @Column(name="GENDER")
    private String gender;

    @NonNull
    @Column(name="BIRTHDATE")
    private LocalDate birthdate;
}
