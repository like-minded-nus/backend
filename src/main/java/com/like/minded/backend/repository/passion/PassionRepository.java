/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.passion;

import com.like.minded.backend.domain.passion.Passion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassionRepository extends JpaRepository<Passion, Integer> {
    @Query(
            "SELECT p FROM Passion p JOIN ProfilePassion pp on p.passionId = pp.passionId WHERE"
                    + " pp.profileId=:profileId")
    List<Passion> findPassionsByProfileId(@Param("profileId") Integer profileId);

    List<Passion> findByPassionNameIn(List<String> passionNames);
}
