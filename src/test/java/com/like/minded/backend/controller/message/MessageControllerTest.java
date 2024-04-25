/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

import com.like.minded.backend.domain.message.Message;
import com.like.minded.backend.dto.message.MarkMessageAsReadDto;
import com.like.minded.backend.dto.message.MessageDto;
import com.like.minded.backend.dto.message.ReadMessageDto;
import com.like.minded.backend.service.message.MessageService;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock private SimpMessagingTemplate simpMessagingTemplate;

    @Mock private MessageService messageService;

    @InjectMocks private MessageController messageController;

    @Test
    void recMessage() {
        Message message = new Message();
        message.setReceiverProfileId(1);

        ResponseEntity<BaseResponse<MessageDto>> expectedResponse = ResponseEntity.ok().build();
        when(messageService.sendMessage(any())).thenReturn(expectedResponse);

        ResponseEntity<BaseResponse<MessageDto>> response = messageController.recMessage(message);

        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void markMessageAsRead() {
        ReadMessageDto payload = new ReadMessageDto();
        payload.setSenderProfileId(1);

        messageController.markMessageAsRead(payload);

        verify(simpMessagingTemplate).convertAndSendToUser(anyString(), eq("/private/read"), any());
    }

    @Test
    void readMessage() {
        MarkMessageAsReadDto markMessageAsReadDto = new MarkMessageAsReadDto();
        ResponseEntity<BaseResponse<String>> expectedResponse = ResponseEntity.ok().build();
        when(messageService.markMessageAsRead(markMessageAsReadDto)).thenReturn(expectedResponse);

        ResponseEntity<BaseResponse<String>> response =
                messageController.readMessage(markMessageAsReadDto);

        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void getMessageBetweenUsers() {
        ResponseEntity<BaseResponse<List<Message>>> expectedResponse = ResponseEntity.ok().build();
        when(messageService.getMessageBetweenUsers(anyInt(), anyInt()))
                .thenReturn(expectedResponse);

        ResponseEntity<BaseResponse<List<Message>>> response =
                messageController.getMessageBetweenUsers(1, 2);

        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void getLatestMessageBetweenUsers() {
        ResponseEntity<BaseResponse<List<Message>>> expectedResponse = ResponseEntity.ok().build();
        when(messageService.getLatestMessageBetweenUsers(anyInt(), anyInt()))
                .thenReturn(expectedResponse);

        ResponseEntity<BaseResponse<List<Message>>> response =
                messageController.getLatestMessageBetweenUsers(1, 2);

        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void getNextMessageId() {
        ResponseEntity<BaseResponse<Integer>> expectedResponse = ResponseEntity.ok().build();
        when(messageService.getNextMessageId()).thenReturn(expectedResponse);

        ResponseEntity<BaseResponse<Integer>> response = messageController.getNextMessageId();

        assertEquals(OK, response.getStatusCode());
    }
}
