/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.voucher;

import com.like.minded.backend.domain.voucher.VoucherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherType, Integer> {
    VoucherType findByVoucherType(Integer voucherType);
}
