/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.ban;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bans")
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ban_sequence")
    @SequenceGenerator(name = "ban_sequence", sequenceName = "ban_sequence", allocationSize = 1)
    @Column(name = "BAN_ID")
    private Integer id;

    @NonNull
    @Column(name = "USER_ID")
    private Integer userId;

    @NonNull
    @Column(name = "BANNED_REASON")
    private String bannedReason;
}
