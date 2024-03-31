package com.like.minded.backend.domain.vendor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @NonNull
    @Column(name="ACTIVITY")
    private String activity;

    @NonNull
    @Column(name="ACTIVITY_TYPE")
    private String activityType;
}
