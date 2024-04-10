/* LikeMinded (C)2024 */
package com.like.minded.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CreatedUpdatedColumns {
    @Column(name = "CREATED_BY")
    private String createdBy = "SYSTEM";

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "UPDATED_BY")
    private String updatedBy = "SYSTEM";

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate = LocalDateTime.now();
}
