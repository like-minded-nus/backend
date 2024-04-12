/* LikeMinded (C)2024 */
package com.like.minded.backend.service.report;

import com.like.minded.backend.domain.report.Report;
import com.like.minded.backend.dto.report.GetReportedProfileDto;
import com.like.minded.backend.dto.report.ReportDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.report.ReportRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final ModelMapper modelMapper;
    private final ReportRepository reportRepository;

    @Override
    public ResponseEntity<BaseResponse<Integer>> reportProfile(ReportDto reportDto)
            throws SQLException, IOException {

        Report newReport =
                Report.builder()
                        .profileId(reportDto.getProfileId())
                        .reportedReason(reportDto.getReportedReason())
                        .build();

        try {
            reportRepository.save(newReport);
        } catch (Exception e) {
            throw new DatabaseTransactionException(
                    "Error saving reported profile into Database", e);
        }

        BaseResponse<Integer> response =
                BaseResponse.<Integer>builder()
                        .status(200)
                        .message("Successfully created reported profile")
                        .payload(reportDto.getProfileId())
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<List<GetReportedProfileDto>>> findReportedProfile()
            throws SQLException, IOException {

        List<GetReportedProfileDto> getReportedProfileDtoList =
                reportRepository.findReportedProfiles();

        BaseResponse<List<GetReportedProfileDto>> response =
                BaseResponse.<List<GetReportedProfileDto>>builder()
                        .status(200)
                        .message("Successfully retrieved reports")
                        .payload(getReportedProfileDtoList)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
