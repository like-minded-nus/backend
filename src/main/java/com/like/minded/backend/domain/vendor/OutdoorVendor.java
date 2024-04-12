/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("OUTDOOR")
@Data
@EqualsAndHashCode(callSuper = true)
public class OutdoorVendor extends Vendor {
    @Column(name = "INTENSITY_LEVEL")
    private String intensityLevel;
}
