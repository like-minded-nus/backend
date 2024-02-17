package com.like.minded.backend.repository.match;

import com.like.minded.backend.domain.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query("SELECT m FROM Match m " +
            "WHERE m.like_1 = TRUE AND m.like_2 = TRUE " +
            "AND m.profileId_1 = :profileId OR m.profileId_2 = :profileId")
    List<Match> findProfileMatches(Integer profileId);

    @Query("SELECT m FROM Match m " +
            "WHERE m.profileId_1 = :profileId_1 AND m.profileId_2 = :profileId_2")
    Match findMatchByProfileIds(Integer profileId_1, Integer profileId_2);
}
