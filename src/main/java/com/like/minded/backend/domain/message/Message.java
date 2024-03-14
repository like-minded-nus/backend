package com.like.minded.backend.domain.message;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String senderProfileId;
    private String receiverProfileId;
    private String text;
    private String dateTime;
    private Status status;
}
