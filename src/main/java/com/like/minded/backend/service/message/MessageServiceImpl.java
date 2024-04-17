/* LikeMinded (C)2024 */
package com.like.minded.backend.service.message;

import com.like.minded.backend.domain.message.Message;
import com.like.minded.backend.domain.message.MessageSequence;
import com.like.minded.backend.dto.message.MarkMessageAsReadDto;
import com.like.minded.backend.dto.message.MessageDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.message.MessageRepository;
import com.like.minded.backend.repository.message.MessageSequenceRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageSequenceRepository messageSequenceRepository;

    @Override
    public ResponseEntity<BaseResponse<MessageDto>> getMessage() {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse<MessageDto>> sendMessage(MessageDto messageDto) {

        Message newMessage =
                Message.builder()
                        .messageId(messageDto.getMessageId())
                        .senderProfileId(messageDto.getSenderProfileId())
                        .receiverProfileId(messageDto.getReceiverProfileId())
                        .text(messageDto.getText())
                        .sentDateTime(messageDto.getSentDateTime())
                        .isRead(messageDto.getIsRead())
                        .build();

        try {
            messageRepository.save(newMessage);
            MessageSequence ms = messageSequenceRepository.findNextMessageSequence();
            ms.setNextMessageId(ms.getNextMessageId() + 1);
            messageSequenceRepository.save(ms);

        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving message into Database", e);
        }

        MessageDto messageResponseBodyDto =
                new MessageDto(
                        newMessage.getMessageId(),
                        newMessage.getSenderProfileId(),
                        newMessage.getReceiverProfileId(),
                        newMessage.getText(),
                        newMessage.getSentDateTime(),
                        newMessage.getIsRead());

        BaseResponse<MessageDto> response =
                BaseResponse.<MessageDto>builder()
                        .status(200)
                        .message("Successfully sent message")
                        .payload(messageResponseBodyDto)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<String>> markMessageAsRead(
            MarkMessageAsReadDto markMessageAsReadDto) {

        int retryCount = 50;
        Optional<Message> messageOptional =
                messageRepository.findById(markMessageAsReadDto.getMessageId());
        ;

        for (int i = 1; i <= retryCount; i++) {
            System.out.println("Retrying " + i);
            messageOptional = messageRepository.findById(markMessageAsReadDto.getMessageId());

            BaseResponse<String> messageIdNotFoundResponse =
                    BaseResponse.<String>builder()
                            .status(200)
                            .message(
                                    "Message "
                                            + markMessageAsReadDto.getMessageId()
                                            + "cannot be found")
                            .build();

            if (i == retryCount && messageOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(messageIdNotFoundResponse);
            }

            if (!messageOptional.isEmpty()) break;
        }

        Message message = messageOptional.get();
        message.setIsRead("Y");

        try {
            messageRepository.save(message);
        } catch (Exception e) {
            throw new DatabaseTransactionException(
                    "Error marking message as read into Database", e);
        }

        String responseMessage = "";

        BaseResponse<String> response =
                BaseResponse.<String>builder()
                        .status(200)
                        .message("Message " + message.getMessageId() + " is read")
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<List<Message>>> getMessageBetweenUsers(
            Integer senderProfileId, Integer receiverProfileId) {

        List<Message> messageList;
        try {
            messageList =
                    messageRepository.findMessageBetweenUsers(senderProfileId, receiverProfileId);
            System.out.println(messageList.toString());
        } catch (Exception e) {
            throw new DatabaseTransactionException("Failed to retrieve message between users", e);
        }

        BaseResponse<List<Message>> response =
                BaseResponse.<List<Message>>builder()
                        .status(200)
                        .message("Successfully retrieve messages between users")
                        .payload(messageList)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<List<Message>>> getLatestMessageBetweenUsers(
            Integer senderProfileId, Integer receiverProfileId) {

        List<Message> messageList;
        try {
            messageList =
                    messageRepository.findLatestMessageBetweenUsers(
                            senderProfileId, receiverProfileId);
            System.out.println(messageList.toString());
        } catch (Exception e) {
            throw new DatabaseTransactionException(
                    "Failed to retrieve latest message between users", e);
        }

        BaseResponse<List<Message>> response =
                BaseResponse.<List<Message>>builder()
                        .status(200)
                        .message("Successfully retrieve latest message between users")
                        .payload(messageList)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<Integer>> getNextMessageId() {
        MessageSequence ms = messageSequenceRepository.findNextMessageSequence();

        BaseResponse<Integer> response =
                BaseResponse.<Integer>builder()
                        .status(200)
                        .message("Successfully retrieve next message id")
                        .payload(ms.getNextMessageId().intValue())
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
