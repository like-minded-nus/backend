/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.report;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_sequence")
    @SequenceGenerator(
            name = "report_sequence",
            sequenceName = "report_sequence",
            allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @NonNull
    @Column(name = "USER_ID")
    private Integer userId;

    @NonNull
    @Column(name = "REPORTED_BY")
    private Integer reportedBy;

    @NonNull
    @Column(name = "REPORTED_REASON")
    private String reportedReason;
}
