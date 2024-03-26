package com.like.minded.backend.domain.vendor;

import com.like.minded.backend.domain.CreatedUpdatedColumns;
import com.like.minded.backend.domain.voucher.Voucher;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vendor")
public class Vendor extends CreatedUpdatedColumns {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_sequence")
    @SequenceGenerator(name="vendor_sequence", sequenceName = "vendor_sequence", allocationSize = 1)
    @Column(name="VENDOR_ID")
    private Integer vendorId;

    @NonNull
    @Column(name="VENDOR_NAME")
    private String vendorName;

    @NonNull
    @Column(name="ACTIVITY_NAME")
    private String activityName;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONE_NUMBER")
    private Integer phoneNumber;

    @Column(name="WEBSITE")
    private String website;

    @OneToMany(mappedBy = "vendorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Voucher> vouchers;
}