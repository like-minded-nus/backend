/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.message;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message_sequence")
public class MessageSequence {
    @Id
    @Column(name = "sequence_id")
    private Integer sequenceId;

    @Column(name = "next_val")
    private Integer nextMessageId;
}
