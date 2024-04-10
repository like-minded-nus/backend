/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.message;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;

@EqualsAndHashCode
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "MESSAGE_ID")
    private Integer messageId;

    @NonNull
    @Column(name = "SENDER_PROFILE_ID")
    private Integer senderProfileId;

    @NonNull
    @Column(name = "RECEIVER_PROFILE_ID")
    private Integer receiverProfileId;

    @NonNull
    @Column(name = "TEXT")
    private String text;

    @NonNull
    @Column(name = "SENT_DATETIME")
    private Timestamp sentDateTime;

    @NonNull
    @Column(name = "IS_READ")
    private String isRead;
}
