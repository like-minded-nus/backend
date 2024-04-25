/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.vendor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.dto.vendor.VendorResponseDto;
import com.like.minded.backend.service.vendor.VendorService;
import com.like.minded.backend.vo.vendor.VendorResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock private VendorService vendorService;

    @InjectMocks private VendorController vendorController;

    @Test
    void createVendor() {
        VendorCreationDto vendorCreationDto = new VendorCreationDto();
        VendorResponse expectedResponse = VendorResponse.builder().build();
        when(vendorService.createVendor(vendorCreationDto))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<VendorResponse> response = vendorController.createVendor(vendorCreationDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
        when(vendorService.getAllVendors()).thenReturn(vendors);

        ResponseEntity<List<Vendor>> response = vendorController.getAllVendors();

        assertEquals(OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getAllVendors_Empty() {
        when(vendorService.getAllVendors()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Vendor>> response = vendorController.getAllVendors();

        assertEquals(NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getVendorById() {
        Vendor vendor = new Vendor();
        when(vendorService.getVendorById(1)).thenReturn(vendor);

        ResponseEntity<Vendor> response = vendorController.getVendorById(1);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getVendorById_NotFound() {
        when(vendorService.getVendorById(1)).thenReturn(null);

        ResponseEntity<Vendor> response = vendorController.getVendorById(1);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getVendorsByPassionIds() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
        when(vendorService.getVendorsByPassionIds(any())).thenReturn(vendors);

        List<Vendor> response = vendorController.getVendorsByPassionIds(Arrays.asList(1, 2));

        assertEquals(2, response.size());
    }

    @Test
    void getVendorsByPassionIdsAndUserPremiumStatus() {
        List<VendorResponseDto> vendors =
                Arrays.asList(new VendorResponseDto(), new VendorResponseDto());
        when(vendorService.getVendorsByPassionIdsAndUserPremiumStatus(any(), anyInt()))
                .thenReturn(vendors);

        List<VendorResponseDto> response =
                vendorController.getVendorsByPassionIdsAndUserPremiumStatus(Arrays.asList(1, 2), 1);

        assertEquals(2, response.size());
    }

    @Test
    void updateVendor() {
        VendorCreationDto updatedVendorDto = new VendorCreationDto();
        VendorResponse expectedResponse = VendorResponse.builder().build();
        when(vendorService.updateVendor(1, updatedVendorDto))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<VendorResponse> response =
                vendorController.updateVendor(1, updatedVendorDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateVendor_NotFound() {
        VendorCreationDto updatedVendorDto = new VendorCreationDto();
        when(vendorService.updateVendor(1, updatedVendorDto))
                .thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<VendorResponse> response =
                vendorController.updateVendor(1, updatedVendorDto);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteVendor() {
        when(vendorService.deleteVendor(1)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<VendorResponse> response = vendorController.deleteVendor(1);

        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void deleteVendor_NotFound() {
        when(vendorService.deleteVendor(1)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<VendorResponse> response = vendorController.deleteVendor(1);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
