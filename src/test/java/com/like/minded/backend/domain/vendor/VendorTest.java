package com.like.minded.backend.domain.vendor;

import com.like.minded.backend.domain.voucher.Voucher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

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

        when(mockVouchers.size()).thenReturn(10);

        Vendor vendor = Vendor.builder()
                .vendorId(vendorId)
                .vendorName(vendorName)
                .activityName(activityName)
                .address(address)
                .phoneNumber(phoneNumber)
                .website(website)
                .vouchers(mockVouchers)
                .build();

        assertNotNull(vendor, "Vendor object should not be null");
        assertEquals(vendorId, vendor.getVendorId(), "Vendor ID should match the provided value");
        assertEquals(vendorName, vendor.getVendorName(), "Vendor Name should match the provided value");
        assertEquals(activityName, vendor.getActivityName(), "Activity Name should match the provided value");
        assertEquals(address, vendor.getAddress(), "Address should match the provided value");
        assertEquals(phoneNumber, vendor.getPhoneNumber(), "Phone Number should match the provided value");
        assertEquals(website, vendor.getWebsite(), "Website should match the provided value");
        assertEquals(10, vendor.getVouchers().size(), "Vouchers list size should match the mocked value");
    }
}
