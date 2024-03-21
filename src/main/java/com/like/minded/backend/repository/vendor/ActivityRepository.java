package com.like.minded.backend.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.like.minded.backend.domain.vendor.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    Activity findByActivity(String activity);
}
