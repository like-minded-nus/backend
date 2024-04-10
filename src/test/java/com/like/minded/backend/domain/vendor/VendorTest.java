/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.vendor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.like.minded.backend.domain.voucher.Voucher;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class VendorTest {

    @Test
    void testVendorCreationAndProperties() {
        Integer vendorId = 1;
        String vendorName = "Test Vendor";
        String activityName = "Biking";
        String address = "Test Address";
        Integer phoneNumber = 87654321;
        String website = "https://test.com";
        List<Voucher> mockVouchers = mock(List.class);
        Integer passionId = 1;

        when(mockVouchers.size()).thenReturn(10);

        Vendor vendor =
                Vendor.builder()
                        .vendorId(vendorId)
                        .vendorName(vendorName)
                        .activityName(activityName)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .website(website)
                        .vouchers(mockVouchers)
                        .passionId(passionId)
                        .build();

        assertNotNull(vendor, "Vendor object should not be null");
        assertEquals(vendorId, vendor.getVendorId(), "Vendor ID should match the provided value");
        assertEquals(
                vendorName, vendor.getVendorName(), "Vendor Name should match the provided value");
        assertEquals(
                activityName,
                vendor.getActivityName(),
                "Activity Name should match the provided value");
        assertEquals(address, vendor.getAddress(), "Address should match the provided value");
        assertEquals(
                phoneNumber,
                vendor.getPhoneNumber(),
                "Phone Number should match the provided value");
        assertEquals(website, vendor.getWebsite(), "Website should match the provided value");
        assertEquals(
                10,
                vendor.getVouchers().size(),
                "Vouchers list size should match the mocked value");
    }

    @Test
    void testEquals() {
        Vendor vendor1 =
                Vendor.builder()
                        .vendorId(1)
                        .vendorName("Test Vendor")
                        .activityName("Hiking")
                        .address("Test Address")
                        .phoneNumber(87654321)
                        .website("https://test.com")
                        .vouchers(Collections.emptyList())
                        .passionId(1)
                        .build();

        Vendor vendor2 =
                Vendor.builder()
                        .vendorId(1)
                        .vendorName("Test Vendor")
                        .activityName("Hiking")
                        .address("Test Address")
                        .phoneNumber(87654321)
                        .website("https://test.com")
                        .vouchers(Collections.emptyList())
                        .passionId(1)
                        .build();

        Vendor vendorDifferent =
                Vendor.builder()
                        .vendorId(2)
                        .vendorName("Outdoor Expeditions")
                        .activityName("Kayaking")
                        .address("Test Address")
                        .phoneNumber(87654321)
                        .website("https://test.com")
                        .vouchers(Collections.emptyList())
                        .passionId(2)
                        .build();

        assertEquals(vendor1, vendor2, "Identical vendors should be considered equal.");
        assertNotEquals(
                vendor1, vendorDifferent, "Different vendors should not be considered equal.");
    }

    @Test
    void testHashCode() {
        Vendor vendor1 =
                Vendor.builder()
                        .vendorId(1)
                        .vendorName("Test Vendor")
                        .activityName("Hiking")
                        .address("Test Address")
                        .phoneNumber(87654321)
                        .website("https://test.com")
                        .vouchers(Collections.emptyList())
                        .passionId(1)
                        .build();

        Vendor vendor2 =
                Vendor.builder()
                        .vendorId(1)
                        .vendorName("Test Vendor")
                        .activityName("Hiking")
                        .address("Test Address")
                        .phoneNumber(87654321)
                        .website("https://test.com")
                        .vouchers(Collections.emptyList())
                        .passionId(1)
                        .build();

        assertEquals(
                vendor1.hashCode(),
                vendor2.hashCode(),
                "Equal vendors must have the same hash code.");

        Vendor vendorDifferent =
                Vendor.builder()
                        .vendorId(2)
                        .vendorName("Outdoor Expeditions")
                        .activityName("Kayaking")
                        .address("Test Address")
                        .phoneNumber(87654321)
                        .website("https://test.com")
                        .vouchers(Collections.emptyList())
                        .passionId(2)
                        .build();

        assertNotEquals(
                vendor1.hashCode(),
                vendorDifferent.hashCode(),
                "Different vendors should ideally have different hash codes.");
    }
}
