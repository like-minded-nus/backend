/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.voucher;

import com.like.minded.backend.domain.voucher.Voucher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    boolean existsByVoucherName(String voucherName);

    List<Voucher> findByVendorId(Integer vendorId);
}
