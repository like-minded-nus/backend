/* LikeMinded (C)2024 */
package com.like.minded.backend.service.report;

import com.like.minded.backend.domain.report.Report;
import com.like.minded.backend.dto.report.GetReportsDto;
import com.like.minded.backend.dto.report.ReportDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.report.ReportRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportServiceImpl implements ReportService {

    ReportRepository reportRepository;

    @Override
    public ResponseEntity<BaseResponse<Integer>> reportUser(ReportDto reportDto) {

        Report newReport =
                Report.builder()
                        .userId(reportDto.getUserId())
                        .reportedBy(reportDto.getReportedBy())
                        .reportedReason(reportDto.getReportedReason())
                        .build();

        try {
            reportRepository.save(newReport);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving report into Database", e);
        }

        BaseResponse<Integer> response =
                BaseResponse.<Integer>builder()
                        .status(200)
                        .message("Successfully created report")
                        .payload(reportDto.getUserId())
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<List<GetReportsDto>>> findReports() {

        List<GetReportsDto> getReportsDtoList = reportRepository.findReports();

        BaseResponse<List<GetReportsDto>> response =
                BaseResponse.<List<GetReportsDto>>builder()
                        .status(200)
                        .message("Successfully retrieved reports")
                        .payload(getReportsDtoList)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
