/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.vendor;

import com.like.minded.backend.domain.vendor.Vendor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    boolean existsByVendorName(String vendorName);

    boolean existsByPhoneNumber(Integer phoneNumber);

    List<Vendor> findVendorsByPassionIdIn(List<Integer> passionIds);
}
