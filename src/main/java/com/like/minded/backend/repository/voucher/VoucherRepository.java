package com.like.minded.backend.repository.voucher;

import com.like.minded.backend.domain.voucher.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    boolean existsByVoucherName(String voucherName);

    List<Voucher> findByVendorId(Integer vendorId);
}
