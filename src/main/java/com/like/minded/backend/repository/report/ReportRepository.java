/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.report;

import com.like.minded.backend.domain.report.Report;
import com.like.minded.backend.dto.report.GetReportsDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(
            "SELECT NEW com.like.minded.backend.dto.report.GetReportsDto(r.id, "
                    + " u.userId, u.username, r.reportedReason) FROM User u JOIN"
                    + " Report r ON r.userId = u.userId")
    List<GetReportsDto> findReports();
}
