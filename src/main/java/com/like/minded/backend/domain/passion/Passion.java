/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.passion;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passions")
public class Passion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passion_sequence")
    @SequenceGenerator(
            name = "passion_sequence",
            sequenceName = "passion_sequence",
            allocationSize = 1)
    @Column(name = "PASSION_ID")
    private Integer passionId;

    @NonNull
    @Column(name = "PASSION_NAME")
    private String passionName;
}
