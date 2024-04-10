/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.profile;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile_passion")
public class ProfilePassion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passion_profile_sequence")
    @SequenceGenerator(
            name = "passion_profile_sequence",
            sequenceName = "passion_profile_sequence",
            allocationSize = 1)
    @Column(name = "PASSION_PROFILE_ID")
    private Integer passionProfileId;

    @NonNull
    @Column(name = "PROFILE_ID")
    private Integer profileId;

    @NonNull
    @Column(name = "PASSION_ID")
    private Integer passionId;
}
