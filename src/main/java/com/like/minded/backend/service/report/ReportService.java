/* LikeMinded (C)2024 */
package com.like.minded.backend.service.report;

import com.like.minded.backend.dto.report.GetReportedProfileDto;
import com.like.minded.backend.dto.report.ReportDto;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ReportService {

    public ResponseEntity<BaseResponse<Integer>> reportProfile(ReportDto reportDto)
            throws SQLException, IOException;

    public ResponseEntity<BaseResponse<List<GetReportedProfileDto>>> findReportedProfile()
            throws SQLException, IOException;
}
