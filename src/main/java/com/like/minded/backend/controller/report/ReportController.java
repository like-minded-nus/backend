/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.report;

import com.like.minded.backend.dto.report.GetReportsDto;
import com.like.minded.backend.dto.report.ReportDto;
import com.like.minded.backend.service.report.ReportService;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@CrossOrigin
public class ReportController {

    @Autowired private ReportService reportService;

    @PostMapping
    public ResponseEntity<BaseResponse<Integer>> reportUser(@RequestBody ReportDto reportDto)
            throws SQLException, IOException {
        return reportService.reportUser(reportDto);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<GetReportsDto>>> getReportedRecords()
            throws SQLException, IOException {
        return reportService.findReports();
    }
}
