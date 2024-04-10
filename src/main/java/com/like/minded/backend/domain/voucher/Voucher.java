/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.voucher;

import com.like.minded.backend.domain.CreatedUpdatedColumns;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voucher")
public class Voucher extends CreatedUpdatedColumns {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucher_sequence")
    @SequenceGenerator(
            name = "voucher_sequence",
            sequenceName = "voucher_sequence",
            allocationSize = 1)
    @Column(name = "VOUCHER_ID")
    private Integer voucherId;

    @NonNull
    @Column(name = "VOUCHER_NAME")
    private String voucherName;

    @Column(name = "VOUCHER_END_DATE")
    private LocalDate voucherEndDate;

    @Column(name = "VOUCHER_DESCRIPTION")
    private String voucherDescription;

    @Column(name = "REDEEM_STATUS")
    private boolean redeemStatus;

    @JoinColumn(name = "VENDOR_ID")
    private Integer vendorId;
}
