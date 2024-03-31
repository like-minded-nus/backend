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

    @Test
    void testEquals() {
        Activity activity1 = new Activity("Hiking", "Outdoor");
        Activity activity2 = new Activity("Hiking", "Outdoor");
        Activity activityDifferent = new Activity("Running", "Outdoor");

        assertEquals(activity1, activity2, "Activities with the same values should be equal.");
        assertNotEquals(activity1, activityDifferent, "Activities with different values should not be equal.");
    }

    @Test
    void testHashCode() {
        Activity activity1 = new Activity("Hiking", "Outdoor");
        Activity activity2 = new Activity("Hiking", "Outdoor");
        Activity activityDifferent = new Activity("Running", "Outdoor");

        assertEquals(activity1.hashCode(), activity2.hashCode(), "Equal activities must have the same hash code.");
        assertNotEquals(activity1.hashCode(), activityDifferent.hashCode(), "Different activities should have different hash codes.");
    }
}
