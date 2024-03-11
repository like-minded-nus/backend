package com.like.minded.backend.repository.vendor;

import com.like.minded.backend.domain.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    boolean existsByVendorName(String vendorName);

    boolean existsByPhoneNumber(Integer phoneNumber);
}
