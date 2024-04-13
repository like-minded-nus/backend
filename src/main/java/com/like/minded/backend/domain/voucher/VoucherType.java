/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.voucher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherType {
    @Id
    @NonNull
    @Column(name = "VOUCHER_TYPE")
    private Integer voucherType;

    @NonNull
    @Column(name = "VOUCHER_TYPE_DESC")
    private String voucherTypeDesc;
}
