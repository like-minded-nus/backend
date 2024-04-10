package com.like.minded.backend.domain.vendor;

import com.like.minded.backend.domain.CreatedUpdatedColumns;
import com.like.minded.backend.domain.passion.Passion;
import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.enums.VendorType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VENDOR_TYPE", discriminatorType = DiscriminatorType.STRING)
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

    @NonNull
    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONE_NUMBER")
    private Integer phoneNumber;

    @Column(name="WEBSITE")
    private String website;

    @NonNull
    @Column(name="PASSION_ID")
    private Integer passionId;

    @Enumerated(EnumType.STRING)
//    @Column(name = "VENDOR_TYPE", columnDefinition = "ENUM('indoor', 'outdoor')", insertable = false, updatable = false)
    @Column(name = "VENDOR_TYPE", insertable = false, updatable = false)
    private VendorType vendorType;

    @OneToMany(mappedBy = "vendorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Voucher> vouchers;
}
