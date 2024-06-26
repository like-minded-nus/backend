/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.message;

import com.like.minded.backend.domain.message.Message;
import com.like.minded.backend.dto.message.MarkMessageAsReadDto;
import com.like.minded.backend.dto.message.MessageDto;
import com.like.minded.backend.dto.message.ReadMessageDto;
import com.like.minded.backend.service.message.MessageService;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RequestMapping("/api/v1/message")
@CrossOrigin
public class MessageController {

    SimpMessagingTemplate simpMessagingTemplate;
    MessageService messageService;

    @MessageMapping("/private-message")
    public ResponseEntity<BaseResponse<MessageDto>> recMessage(@Payload Message message) {
        // topic: /user/profileId/private
        simpMessagingTemplate.convertAndSendToUser(
                message.getReceiverProfileId().toString(), "/private", message);
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
    public void markMessageAsRead(@Payload ReadMessageDto payload) {
        simpMessagingTemplate.convertAndSendToUser(
                payload.getSenderProfileId().toString(), "/private/read", payload.getMessageId());
    }

    @PutMapping("/read")
    public ResponseEntity<BaseResponse<String>> readMessage(
            @RequestBody MarkMessageAsReadDto markMessageAsReadDto) {
        return messageService.markMessageAsRead(markMessageAsReadDto);
    }

    @GetMapping("/{senderProfileId}/{receiverProfileId}")
    public ResponseEntity<BaseResponse<List<Message>>> getMessageBetweenUsers(
            @PathVariable Integer senderProfileId, @PathVariable Integer receiverProfileId) {
        return messageService.getMessageBetweenUsers(senderProfileId, receiverProfileId);
    }

    @GetMapping("/latest/{senderProfileId}/{receiverProfileId}")
    public ResponseEntity<BaseResponse<List<Message>>> getLatestMessageBetweenUsers(
            @PathVariable Integer senderProfileId, @PathVariable Integer receiverProfileId) {
        return messageService.getLatestMessageBetweenUsers(senderProfileId, receiverProfileId);
    }

    @GetMapping("/sequence")
    public ResponseEntity<BaseResponse<Integer>> getNextMessageId() {
        return messageService.getNextMessageId();
    }
}
