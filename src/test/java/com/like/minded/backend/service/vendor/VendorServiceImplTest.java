/* LikeMinded (C)2024 */
package com.like.minded.backend.service.vendor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.domain.vendor.IndoorVendor;
import com.like.minded.backend.domain.vendor.OutdoorVendor;
import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.domain.vendor.creator.IndoorVendorCreator;
import com.like.minded.backend.domain.vendor.creator.OutdoorVendorCreator;
import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.domain.voucher.VoucherType;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.dto.vendor.VendorResponseDto;
import com.like.minded.backend.enums.VendorType;
import com.like.minded.backend.exception.VendorException;
import com.like.minded.backend.repository.vendor.VendorRepository;
import com.like.minded.backend.service.user.UserContext;
import com.like.minded.backend.vo.vendor.VendorResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class VendorServiceImplTest {

    @Mock VendorRepository vendorRepository;

    @Mock IndoorVendorCreator indoorVendorCreator;
    @Mock OutdoorVendorCreator outdoorVendorCreator;

    @Mock UserContext userContext;
    @InjectMocks VendorServiceImpl vendorService;

    @Test
    void createVendorIndoorShouldSucceed() {
        VendorCreationDto dto =
                new VendorCreationDto(
                        "VendorName",
                        "Activity",
                        "Address",
                        87654321,
                        "www.example.com",
                        1,
                        VendorType.INDOOR,
                        "High",
                        null);

        IndoorVendor expectedVendor = new IndoorVendor();
        expectedVendor.setVendorName("VendorName");
        expectedVendor.setActivityName("Activity");
        expectedVendor.setAddress("Address");
        expectedVendor.setPhoneNumber(87654321);
        expectedVendor.setWebsite("www.example.com");
        expectedVendor.setPassionId(1);
        expectedVendor.setVendorType(VendorType.INDOOR);
        expectedVendor.setConversationFriendly("High");

        when(indoorVendorCreator.createVendor(dto)).thenReturn(expectedVendor);
        when(vendorRepository.save(any(Vendor.class))).thenReturn(expectedVendor);

        ResponseEntity<VendorResponse> response = vendorService.createVendor(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Successfully created vendor", response.getBody().getMessage());

        verify(indoorVendorCreator).createVendor(dto);
        verify(vendorRepository).save(expectedVendor);
    }

    @Test
    void createVendorOutdoorShouldSucceed() {
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

        OutdoorVendor expectedVendor = new OutdoorVendor();
        expectedVendor.setVendorName("VendorName");
        expectedVendor.setActivityName("Activity");
        expectedVendor.setAddress("Address");
        expectedVendor.setPhoneNumber(87654321);
        expectedVendor.setWebsite("www.example.com");
        expectedVendor.setPassionId(1);
        expectedVendor.setVendorType(VendorType.OUTDOOR);
        expectedVendor.setIntensityLevel("High");

        when(outdoorVendorCreator.createVendor(dto)).thenReturn(expectedVendor);
        when(vendorRepository.save(any(Vendor.class))).thenReturn(expectedVendor);

        ResponseEntity<VendorResponse> response = vendorService.createVendor(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Successfully created vendor", response.getBody().getMessage());

        verify(outdoorVendorCreator).createVendor(dto);
        verify(vendorRepository).save(expectedVendor);
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
    void testGetVendorsByPassionIdsAndUserPremiumStatus_NormalUser() {
        // Arrange
        Integer userPremiumStatus = 0;
        List<Integer> passionIds = Arrays.asList(1, 2);
        List<Vendor> vendors =
                Arrays.asList(
                        new Vendor(
                                1,
                                "VendorName1",
                                "Activity1",
                                "Address1",
                                98765432,
                                "website1.com",
                                1,
                                VendorType.INDOOR,
                                Collections.emptyList()),
                        new Vendor(
                                2,
                                "VendorName2",
                                "Activity2",
                                "Address2",
                                87654321,
                                "website2.com",
                                2,
                                VendorType.OUTDOOR,
                                Collections.emptyList()));
        when(vendorRepository.findAll()).thenReturn(vendors);

        // Act
        List<VendorResponseDto> result =
                vendorService.getVendorsByPassionIdsAndUserPremiumStatus(
                        passionIds, userPremiumStatus);

        // Assert
        assertEquals(2, result.size());
        assertEquals("VendorName1", result.get(0).getVendorName());
        assertEquals("VendorName2", result.get(1).getVendorName());
    }

    @Test
    void testGetVendorsByPassionIdsAndUserPremiumStatus_PremiumUser() {
        // Arrange
        Integer userPremiumStatus = 1;
        List<Integer> passionIds = Arrays.asList(1, 2);
        List<Vendor> vendors =
                Arrays.asList(
                        new Vendor(
                                1,
                                "VendorName1",
                                "Activity1",
                                "Address1",
                                98765432,
                                "website1.com",
                                1,
                                VendorType.INDOOR,
                                Collections.emptyList()),
                        new Vendor(
                                2,
                                "VendorName2",
                                "Activity2",
                                "Address2",
                                87654321,
                                "website2.com",
                                2,
                                VendorType.OUTDOOR,
                                Collections.emptyList()));
        when(vendorRepository.findAll())
                .thenReturn(
                        vendors.stream()
                                .filter(v -> passionIds.contains(v.getPassionId()))
                                .collect(Collectors.toList()));

        // Act
        List<VendorResponseDto> result =
                vendorService.getVendorsByPassionIdsAndUserPremiumStatus(
                        passionIds, userPremiumStatus);

        // Assert
        assertEquals(2, result.size());
        assertEquals("VendorName1", result.get(0).getVendorName());
        assertEquals("VendorName2", result.get(1).getVendorName());
    }

    @Test
    void testVouchersProcessedCorrectly() {
        // Arrange
        Integer userPremiumStatus = 1;
        List<Integer> passionIds = List.of(1);
        Voucher voucher1 =
                new Voucher(
                        1,
                        "voucher1",
                        new VoucherType(1, "voucherType1"),
                        75,
                        LocalDate.now().plusDays(30),
                        false,
                        1);
        Voucher voucher2 =
                new Voucher(
                        2,
                        "voucher2",
                        new VoucherType(1, "voucherType2"),
                        158,
                        LocalDate.now().plusDays(30),
                        true,
                        1);
        List<Voucher> vouchers = Arrays.asList(voucher1, voucher2);
        Vendor vendor =
                new Vendor(
                        1,
                        "VendorName1",
                        "Activity1",
                        "Address1",
                        98765432,
                        "website1.com",
                        1,
                        VendorType.INDOOR,
                        vouchers);

        when(vendorRepository.findAll()).thenReturn(List.of(vendor));

        // Act
        List<VendorResponseDto> result =
                vendorService.getVendorsByPassionIdsAndUserPremiumStatus(
                        passionIds, userPremiumStatus);

        // Assert
        assertEquals(1, result.size());
        assertEquals(2, result.getFirst().getVouchers().size());
        assertEquals(90, result.getFirst().getVouchers().get(0).getVoucherAmount());
        assertEquals(190, result.getFirst().getVouchers().get(1).getVoucherAmount());
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

    @Test
    void updateVendor() {
        // Create an instance of IndoorVendor with vendorType set
        IndoorVendor existingVendor = new IndoorVendor();
        existingVendor.setVendorId(1);
        existingVendor.setVendorType(VendorType.INDOOR);

        VendorCreationDto updatedVendorDto = new VendorCreationDto();
        updatedVendorDto.setVendorType(VendorType.INDOOR);
        updatedVendorDto.setVendorName("New Name");
        updatedVendorDto.setActivityName("New Activity");
        updatedVendorDto.setAddress("New Address");
        updatedVendorDto.setPassionId(1);

        when(vendorRepository.findById(1)).thenReturn(Optional.of(existingVendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(existingVendor);

        ResponseEntity<VendorResponse> response = vendorService.updateVendor(1, updatedVendorDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully updated vendor", response.getBody().getMessage());
        verify(vendorRepository).save(any(Vendor.class));
    }

    @Test
    void updateVendorNotFound() {
        when(vendorRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<VendorResponse> response =
                vendorService.updateVendor(1, new VendorCreationDto());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getVendorsByPassionIds() {
        Vendor vendor1 = new IndoorVendor();
        vendor1.setPassionId(1);
        Vendor vendor2 = new OutdoorVendor();
        vendor2.setPassionId(2);

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        List<Vendor> result = vendorService.getVendorsByPassionIds(Arrays.asList(1, 3));

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getPassionId());
        verify(vendorRepository).findAll();
    }

    @Test
    void deleteVendor() {
        Vendor existingVendor = new IndoorVendor();
        existingVendor.setVendorId(1);

        when(vendorRepository.findById(1)).thenReturn(Optional.of(existingVendor));
        doNothing().when(vendorRepository).deleteById(1);

        ResponseEntity<VendorResponse> response = vendorService.deleteVendor(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vendor deleted successfully", response.getBody().getMessage());
        verify(vendorRepository).deleteById(1);
    }

    @Test
    void deleteVendorNotFound() {
        when(vendorRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<VendorResponse> response = vendorService.deleteVendor(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
