package com.like.minded.backend.domain.vendor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("INDOOR")
@Data
@EqualsAndHashCode(callSuper = true)
public class IndoorVendor extends Vendor{
    @Column(name = "CONVERSATION_FRIENDLY")
    private String conversationFriendly;
}
