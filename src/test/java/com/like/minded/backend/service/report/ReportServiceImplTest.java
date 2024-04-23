/* LikeMinded (C)2024 */
package com.like.minded.backend.service.report;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.domain.report.Report;
import com.like.minded.backend.dto.report.GetReportsDto;
import com.like.minded.backend.dto.report.ReportDto;
import com.like.minded.backend.repository.report.ReportRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock private ReportRepository reportRepository;

    @InjectMocks private ReportServiceImpl reportService;

    @Test
    void reportUser() {
        ReportDto reportDto = new ReportDto(1, 2, 3, "Inappropriate behavior");
        Report report =
                new Report(
                        1,
                        reportDto.getUserId(),
                        reportDto.getReportedBy(),
                        reportDto.getReportedReason());
        when(reportRepository.save(any(Report.class))).thenReturn(report);

        ResponseEntity<BaseResponse<Integer>> response = reportService.reportUser(reportDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Integer.valueOf(2), response.getBody().getPayload());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void findReports() {
        List<GetReportsDto> reportsList =
                Arrays.asList(new GetReportsDto(1, 2, "testUser", 3, "Spam"));
        when(reportRepository.findReports()).thenReturn(reportsList);

        ResponseEntity<BaseResponse<List<GetReportsDto>>> response = reportService.findReports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getPayload().size());
        verify(reportRepository).findReports();
    }
}
