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

    @Modifying
    @Query("DELETE FROM ProfilePassion WHERE profileId = :profileId")
    void deleteProfilePassionByProfileId(Integer profileId);

}
