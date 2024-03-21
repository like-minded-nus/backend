package com.like.minded.backend.service.message;

import com.like.minded.backend.domain.message.Message;
import com.like.minded.backend.dto.message.MessageDto;
import com.like.minded.backend.vo.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface MessageService {
    public ResponseEntity<BaseResponse<MessageDto>> getMessage();
    public ResponseEntity<BaseResponse<MessageDto>> sendMessage(MessageDto messageDto);

    public ResponseEntity<BaseResponse<String>> markMessageAsRead(Integer messageId);

    ResponseEntity<BaseResponse<List<Message>>> getMessageBetweenUsers(Integer senderProfileId, Integer receiverProfileId);

    ResponseEntity<BaseResponse<Integer>> getNextMessageId();
}
