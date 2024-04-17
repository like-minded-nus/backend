/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.report;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReportTest {

    @Test
    void getId() {
        Report report = new Report();
        report.setId(1);
        assertEquals(1, report.getId());
    }

    @Test
    void getUserId() {
        Report report = new Report();
        report.setUserId(1001);
        assertEquals(1001, report.getUserId());
    }

    @Test
    void getReportedBy() {
        Report report = new Report();
        report.setReportedBy(1002);
        assertEquals(1002, report.getReportedBy());
    }

    @Test
    void getReportedReason() {
        Report report = new Report();
        report.setReportedReason("Inappropriate behavior");
        assertEquals("Inappropriate behavior", report.getReportedReason());
    }

    @Test
    void setId() {
        Report report = new Report();
        report.setId(2);
        assertEquals(2, report.getId());
    }

    @Test
    void setUserId() {
        Report report = new Report();
        report.setUserId(1003);
        assertEquals(1003, report.getUserId());
    }

    @Test
    void setReportedBy() {
        Report report = new Report();
        report.setReportedBy(1004);
        assertEquals(1004, report.getReportedBy());
    }

    @Test
    void setReportedReason() {
        Report report = new Report();
        report.setReportedReason("Spam");
        assertEquals("Spam", report.getReportedReason());
    }

    @Test
    void testToString() {
        Report report = new Report(1, 1005, 1006, "Harassment");
        String expected = "Report(id=1, userId=1005, reportedBy=1006, reportedReason=Harassment)";
        assertEquals(expected, report.toString());
    }

    @Test
    void testEquals() {
        Report report1 = new Report(1, 1007, 1008, "Cheating");
        Report report2 = new Report(1, 1007, 1008, "Cheating");
        assertEquals(report1, report2);
    }

    @Test
    void canEqual() {
        Report report1 = new Report(1, 1009, 1010, "Exploiting");
        Report report2 = new Report(1, 1009, 1010, "Exploiting");
        assertTrue(report1.canEqual(report2) && report2.canEqual(report1));
    }

    @Test
    void testHashCode() {
        Report report = new Report(1, 1011, 1012, "Toxicity");
        assertNotNull(report.hashCode());
    }

    @Test
    void builder() {
        Report report =
                Report.builder()
                        .id(3)
                        .userId(1013)
                        .reportedBy(1014)
                        .reportedReason("Griefing")
                        .build();
        assertNotNull(report);
        assertEquals(3, report.getId());
        assertEquals(1013, report.getUserId());
        assertEquals(1014, report.getReportedBy());
        assertEquals("Griefing", report.getReportedReason());
    }
}
