/* LikeMinded (C)2024 */
package com.like.minded.backend.service.report;

import com.like.minded.backend.dto.report.GetReportsDto;
import com.like.minded.backend.dto.report.ReportDto;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ReportService {

    public ResponseEntity<BaseResponse<Integer>> reportUser(ReportDto reportDto);

    public ResponseEntity<BaseResponse<List<GetReportsDto>>> findReports();
}
