/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.ban;

import com.like.minded.backend.domain.ban.Ban;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends JpaRepository<Ban, Integer> {

    @Query(
            "SELECT NEW com.like.minded.backend.dto.ban.GetBannedUsersDto(b.id, "
                    + " u.userId, u.username, b.bannedReason) FROM User u JOIN"
                    + " Ban b ON b.userId = u.userId")
    List<GetBannedUsersDto> findBannedUsers();

    List<Ban> findByUserId(Integer userId);
}
