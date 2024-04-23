/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.vendor;

import static org.junit.jupiter.api.Assertions.*;

import com.like.minded.backend.enums.VendorType;
import org.junit.jupiter.api.Test;

class VendorCreationDtoTest {

    @Test
    void testEquals() {
        VendorType vendorType1 = VendorType.OUTDOOR;
        VendorCreationDto dto1 =
                new VendorCreationDto(
                        "VendorA",
                        "Yoga",
                        "123 Elm St",
                        1234567890,
                        "www.vendora.com",
                        1,
                        vendorType1,
                        "High",
                        "Yes");
        VendorCreationDto dto2 =
                new VendorCreationDto(
                        "VendorA",
                        "Yoga",
                        "123 Elm St",
                        1234567890,
                        "www.vendora.com",
                        1,
                        vendorType1,
                        "High",
                        "Yes");
        VendorCreationDto dto3 =
                new VendorCreationDto(
                        "VendorB",
                        "Dance",
                        "456 Oak St",
                        987654321,
                        "www.vendorb.com",
                        2,
                        VendorType.INDOOR,
                        "Medium",
                        "No");

        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);
        assertNotEquals(dto1, dto3);
        assertNotEquals(null, dto2);
        assertNotEquals(dto2, new Object());
    }

    @Test
    void testHashCode() {
        VendorType vendorType1 = VendorType.INDOOR;
        VendorCreationDto dto1 =
                new VendorCreationDto(
                        "VendorA",
                        "Yoga",
                        "123 Elm St",
                        1234567890,
                        "www.vendora.com",
                        1,
                        vendorType1,
                        "High",
                        "Yes");
        VendorCreationDto dto2 =
                new VendorCreationDto(
                        "VendorA",
                        "Yoga",
                        "123 Elm St",
                        1234567890,
                        "www.vendora.com",
                        1,
                        vendorType1,
                        "High",
                        "Yes");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
