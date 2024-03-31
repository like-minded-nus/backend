package com.like.minded.backend.domain.vendor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    void testActivityCreationAndProperties() {
        String activity = "Hiking";
        String activityType = "Outdoor";

        Activity activityInstance = new Activity(activity, activityType);

        assertNotNull(activityInstance, "Activity object should not be null");
        assertEquals(activity, activityInstance.getActivity(), "Activity should match the provided value");
        assertEquals(activityType, activityInstance.getActivityType(), "Activity type should match the provided value");
    }
}
