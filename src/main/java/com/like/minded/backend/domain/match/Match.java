package com.like.minded.backend.domain.match;

import com.like.minded.backend.domain.CreatedUpdatedColumns;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="matches")
public class Match extends CreatedUpdatedColumns {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_sequence")
    @SequenceGenerator(name="match_sequence", sequenceName = "match_sequence", allocationSize = 1)
    @Column(name="MATCH_ID")
    private Integer matchId;

    @NonNull
    @Column(name="PROFILE_ID_1")
    private Integer profileId_1;

    @NonNull
    @Column(name="PROFILE_ID_2")
    private Integer profileId_2;

    @NonNull
    @Column(name="LIKE_1")
    private Boolean like_1 = false;

    @NonNull
    @Column(name="LIKE_2")
    private Boolean like_2 = false;
}
