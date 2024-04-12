/* LikeMinded (C)2024 */
package com.like.minded.backend.service.vendor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.enums.VendorType;
import com.like.minded.backend.exception.VendorException;
import com.like.minded.backend.repository.vendor.VendorRepository;
import com.like.minded.backend.vo.vendor.VendorResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    @Mock private VendorRepository vendorRepository;

    @InjectMocks private VendorServiceImpl vendorService;

    @Test
    void createVendorShouldSucceed() {
        VendorCreationDto dto =
                new VendorCreationDto(
                        "VendorName",
                        "Activity",
                        "Address",
                        87654321,
                        "www.example.com",
                        1,
                        VendorType.OUTDOOR,
                        "High",
                        null);

        when(vendorRepository.existsByVendorName(anyString())).thenReturn(false);
        when(vendorRepository.existsByPhoneNumber(anyInt())).thenReturn(false);
        when(vendorRepository.save(any(Vendor.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<VendorResponse> response = vendorService.createVendor(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Successfully created vendor", response.getBody().getMessage());

        verify(vendorRepository).save(any(Vendor.class));
    }

    @Test
    void createVendorShouldFailWhenNameExists() {
        VendorCreationDto dto =
                new VendorCreationDto(
                        "VendorName",
                        "Activity",
                        "Address",
                        87654321,
                        "www.example.com",
                        1,
                        VendorType.INDOOR,
                        null,
                        "Yes");

        when(vendorRepository.existsByVendorName(dto.getVendorName())).thenReturn(true);

        assertThrows(VendorException.class, () -> vendorService.createVendor(dto));
    }

    @Test
    void getVendorByIdShouldReturnVendor() {
        Integer vendorId = 1;
        Vendor vendor = new Vendor();
        vendor.setVendorId(vendorId);

        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(vendor));

        Vendor foundVendor = vendorService.getVendorById(vendorId);

        assertNotNull(foundVendor);
        assertEquals(vendorId, foundVendor.getVendorId());

        verify(vendorRepository).findById(vendorId);
    }

    @Test
    void getAllVendorsShouldReturnList() {
        Vendor vendor1 = new Vendor();
        Vendor vendor2 = new Vendor();
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<Vendor> foundVendors = vendorService.getAllVendors();

        assertFalse(foundVendors.isEmpty());
        assertEquals(2, foundVendors.size());

        verify(vendorRepository).findAll();
    }
}
