package com.like.minded.backend.repository.profile;

import com.like.minded.backend.domain.profile.ProfilePassion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilePassionRepository extends JpaRepository<ProfilePassion, Integer> {

    @Query("SELECT a.passionName FROM Passion a JOIN ProfilePassion b ON b.passionId = a.passionId WHERE b.profileId = :profileId")
    List<String> findProfilePassionNameByProfileId(Integer profileId);

    @Query("SELECT p1.profileId AS MatchProfileId, COUNT(p1.passionId) AS SimilarityScore " +
            "FROM ProfilePassion p1 " +
            "JOIN ProfilePassion p2 ON p1.passionId = p2.passionId AND p2.profileId = :profileId " +
            "JOIN Profile u1 ON p1.profileId = u1.profileId " +
            "JOIN Profile u2 ON p2.profileId = u2.profileId AND u1.gender != u2.gender " +
            "WHERE p1.profileId != :profileId " +
            "GROUP BY p1.profileId " +
            "ORDER BY SimilarityScore DESC, MatchProfileId ASC")
    List<String> findProfilePassionMatchesByProfileId(Integer profileId);

    @Modifying
    @Query("DELETE FROM ProfilePassion WHERE profileId = :profileId")
    void deleteProfilePassionByProfileId(Integer profileId);

}
