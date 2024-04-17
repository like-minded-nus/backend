/* LikeMinded (C)2024 */
package com.like.minded.backend.service.message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.domain.message.Message;
import com.like.minded.backend.domain.message.MessageSequence;
import com.like.minded.backend.dto.message.MarkMessageAsReadDto;
import com.like.minded.backend.dto.message.MessageDto;
import com.like.minded.backend.repository.message.MessageRepository;
import com.like.minded.backend.repository.message.MessageSequenceRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock private MessageRepository messageRepository;

    @Mock private MessageSequenceRepository messageSequenceRepository;

    @InjectMocks private MessageServiceImpl messageService;

    @Test
    void sendMessage() {
        MessageDto messageDto =
                new MessageDto(1, 2, 3, "Hello", new Timestamp(System.currentTimeMillis()), "N");
        Message message =
                new Message(1, 2, 3, "Hello", new Timestamp(System.currentTimeMillis()), "N");
        MessageSequence ms = new MessageSequence(1, 2);
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        when(messageSequenceRepository.findNextMessageSequence()).thenReturn(ms);

        ResponseEntity<BaseResponse<MessageDto>> response = messageService.sendMessage(messageDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messageDto, response.getBody().getPayload());
        verify(messageRepository).save(any(Message.class));
        verify(messageSequenceRepository).findNextMessageSequence();
    }

    @Test
    void markMessageAsRead() {
        MarkMessageAsReadDto markMessageAsReadDto = new MarkMessageAsReadDto(1);

        Message message =
                new Message(1, 2, 3, "Read me", new Timestamp(System.currentTimeMillis()), "N");
        when(messageRepository.findById(1)).thenReturn(Optional.of(message));

        ResponseEntity<BaseResponse<String>> response =
                messageService.markMessageAsRead(markMessageAsReadDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(messageRepository).save(message);
    }

    @Test
    void getMessageBetweenUsers() {
        List<Message> messages =
                Arrays.asList(
                        new Message(
                                1,
                                2,
                                3,
                                "Conversation",
                                new Timestamp(System.currentTimeMillis()),
                                "Y"));
        when(messageRepository.findMessageBetweenUsers(2, 3)).thenReturn(messages);

        ResponseEntity<BaseResponse<List<Message>>> response =
                messageService.getMessageBetweenUsers(2, 3);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().getPayload().isEmpty());
        assertEquals("Conversation", response.getBody().getPayload().get(0).getText());
    }

    @Test
    void getLatestMessageBetweenUsers() {
        List<Message> messages =
                Arrays.asList(
                        new Message(
                                1, 2, 3, "Latest", new Timestamp(System.currentTimeMillis()), "Y"));
        when(messageRepository.findLatestMessageBetweenUsers(2, 3)).thenReturn(messages);

        ResponseEntity<BaseResponse<List<Message>>> response =
                messageService.getLatestMessageBetweenUsers(2, 3);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().getPayload().isEmpty());
        assertEquals("Latest", response.getBody().getPayload().get(0).getText());
    }

    @Test
    void getNextMessageId() {
        MessageSequence ms = new MessageSequence(1, 100);
        when(messageSequenceRepository.findNextMessageSequence()).thenReturn(ms);

        ResponseEntity<BaseResponse<Integer>> response = messageService.getNextMessageId();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100, response.getBody().getPayload());
    }
}
