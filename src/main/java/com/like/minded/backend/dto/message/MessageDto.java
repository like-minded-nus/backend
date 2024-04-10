/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.message;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Integer messageId;
    private Integer senderProfileId;
    private Integer receiverProfileId;
    private String text;
    private Timestamp sentDateTime;
    private String isRead;
}
