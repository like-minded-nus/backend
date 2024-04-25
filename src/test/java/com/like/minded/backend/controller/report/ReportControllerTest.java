/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

import com.like.minded.backend.dto.report.GetReportsDto;
import com.like.minded.backend.dto.report.ReportDto;
import com.like.minded.backend.service.report.ReportService;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @Mock ReportService reportService;

    @InjectMocks ReportController reportController;

    @Test
    void reportUser() {
        ReportDto reportDto = new ReportDto();
        BaseResponse<Integer> response = BaseResponse.<Integer>builder().build();
        when(reportService.reportUser(reportDto)).thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<Integer>> result = reportController.reportUser(reportDto);
        assertEquals(OK, result.getStatusCode());
    }

    @Test
    void getReportedRecords() {
        BaseResponse<List<GetReportsDto>> response =
                BaseResponse.<List<GetReportsDto>>builder().build();
        when(reportService.findReports()).thenReturn(new ResponseEntity<>(response, OK));
        ResponseEntity<BaseResponse<List<GetReportsDto>>> result =
                reportController.getReportedRecords();
        assertEquals(OK, result.getStatusCode());
    }
}
