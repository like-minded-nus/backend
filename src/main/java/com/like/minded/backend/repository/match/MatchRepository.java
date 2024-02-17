package com.like.minded.backend.repository.match;

import com.like.minded.backend.domain.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query("SELECT m FROM Match m " +
            "WHERE m.profileId_1 = :profileId OR m.profileId_2 = :profileId " +
            "AND m.like_1 = TRUE AND m.like_2 = TRUE")
    List<Match> findMatchesByProfileId(Integer profileId);
}
