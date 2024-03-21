package com.like.minded.backend.controller.message;

import com.like.minded.backend.dto.message.GetMessageRequestBodyDto;
import com.like.minded.backend.dto.message.ReadMessageDto;
import com.like.minded.backend.dto.profile.ProfileResponseBodyDto;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.like.minded.backend.domain.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.like.minded.backend.service.message.MessageService;
import com.like.minded.backend.dto.message.MessageDto;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/api/v1/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private MessageService messageService;

    @MessageMapping("/private-message")
    public ResponseEntity<BaseResponse<MessageDto>> recMessage(@Payload Message message){
        // topic: /user/profileId/private
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverProfileId().toString(),"/private",  message);
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageId(message.getMessageId());
        messageDto.setSenderProfileId(message.getSenderProfileId());
        messageDto.setReceiverProfileId(message.getReceiverProfileId());
        messageDto.setText(message.getText());
        messageDto.setSentDateTime(message.getSentDateTime());
        messageDto.setIsRead(message.getIsRead());
        return messageService.sendMessage(messageDto);
    }

    @MessageMapping("/message-read")
    public ResponseEntity<BaseResponse<String>>  markMessageAsRead(@Payload ReadMessageDto payload) {
        simpMessagingTemplate.convertAndSendToUser(payload.getSenderProfileId().toString(),"/private/read",  payload.getMessageId());
        return messageService.markMessageAsRead(payload.getMessageId());
    }

    @GetMapping("/{senderProfileId}/{receiverProfileId}")
    public ResponseEntity<BaseResponse<List<Message>>> getMessageBetweenUsers(@PathVariable Integer senderProfileId , @PathVariable Integer receiverProfileId) throws SQLException, IOException {
        return messageService.getMessageBetweenUsers(senderProfileId , receiverProfileId);
    }

    @GetMapping("/sequence")
    public ResponseEntity<BaseResponse<Integer>> getNextMessageId() throws SQLException, IOException {
        return messageService.getNextMessageId();
    }
}
