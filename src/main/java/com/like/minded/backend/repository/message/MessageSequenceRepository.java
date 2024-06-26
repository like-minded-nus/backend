/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.message;

import com.like.minded.backend.domain.message.MessageSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageSequenceRepository extends JpaRepository<MessageSequence, Integer> {

    @Query("SELECT m FROM MessageSequence m where m.sequenceId = 1 ")
    MessageSequence findNextMessageSequence();
}
