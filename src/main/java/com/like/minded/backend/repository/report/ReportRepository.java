/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.report;

import com.like.minded.backend.domain.report.Report;
import com.like.minded.backend.dto.report.GetReportedProfileDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(
            "SELECT NEW com.like.minded.backend.dto.report.GetReportedProfileDto(r.id, p.profileId,"
                    + " p.userId, p.displayName, r.reportedReason) FROM Profile p JOIN Report r ON"
                    + " r.profileId = p.profileId")
    List<GetReportedProfileDto> findReportedProfiles();
}
