package com.like.minded.backend.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import com.like.minded.backend.domain.message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        // topic: /user/profileId/private
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverProfileId(),"/private",  message);
        System.out.println(message.toString());
        return message;
    }
}
