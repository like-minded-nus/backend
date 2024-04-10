/* LikeMinded (C)2024 */
package com.like.minded.backend.repository.vendor;

import com.like.minded.backend.domain.vendor.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    Activity findByActivity(String activity);
}
