/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.vendor;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.dto.vendor.VendorResponseDto;
import com.like.minded.backend.service.vendor.VendorService;
import com.like.minded.backend.vo.vendor.VendorResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/v1/vendors")
@CrossOrigin(origins = "*")
public class VendorController {

    VendorService vendorService;

    @PostMapping("/create_vendor")
    public ResponseEntity<VendorResponse> createVendor(
            @RequestBody VendorCreationDto vendorCreationDto) {
        return vendorService.createVendor(vendorCreationDto);
    }

    @GetMapping("")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        if (vendors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vendors);
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Integer vendorId) {
        Vendor vendor = vendorService.getVendorById(vendorId);
        if (vendor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vendor);
    }

    @GetMapping("/byPassionIds")
    public List<Vendor> getVendorsByPassionIds(@RequestParam List<Integer> passionIds) {
        return vendorService.getVendorsByPassionIds(passionIds);
    }

    @GetMapping("/byPassionIdsAndUserPremiumStatus")
    public List<VendorResponseDto> getVendorsByPassionIdsAndUserPremiumStatus(
            @RequestParam List<Integer> passionIds, @RequestParam Integer userPremiumStatus) {
        return vendorService.getVendorsByPassionIdsAndUserPremiumStatus(
                passionIds, userPremiumStatus);
    }

    @PutMapping("/{vendorId}")
    public ResponseEntity<VendorResponse> updateVendor(
            @PathVariable Integer vendorId, @RequestBody VendorCreationDto updatedVendorDto) {
        ResponseEntity<VendorResponse> responseEntity =
                vendorService.updateVendor(vendorId, updatedVendorDto);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{vendorId}")
    public ResponseEntity<VendorResponse> deleteVendor(@PathVariable Integer vendorId) {
        ResponseEntity<VendorResponse> responseEntity = vendorService.deleteVendor(vendorId);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return responseEntity;
    }
}
