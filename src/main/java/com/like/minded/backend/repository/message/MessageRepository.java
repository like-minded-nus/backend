package com.like.minded.backend.repository.message;

import com.like.minded.backend.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m " +
            "WHERE (m.senderProfileId = :senderProfileId AND m.receiverProfileId = :receiverProfileId) " +
            "   OR (m.senderProfileId = :receiverProfileId AND m.receiverProfileId = :senderProfileId) " +
            "ORDER BY m.sentDateTime")
    List<Message> findMessageBetweenUsers(Integer senderProfileId, Integer receiverProfileId);

    @Query("SELECT m FROM Message m " +
            "WHERE (m.senderProfileId = :senderProfileId AND m.receiverProfileId = :receiverProfileId) " +
            "   OR (m.senderProfileId = :receiverProfileId AND m.receiverProfileId = :senderProfileId) " +
            "ORDER BY m.sentDateTime DESC " +
            "LIMIT 1")
    List<Message> findLatestMessageBetweenUsers(Integer senderProfileId, Integer receiverProfileId);
}
