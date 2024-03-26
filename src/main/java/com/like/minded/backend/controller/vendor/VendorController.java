package com.like.minded.backend.controller.vendor;

import com.like.minded.backend.domain.vendor.Vendor;
import com.like.minded.backend.dto.vendor.VendorCreationDto;
import com.like.minded.backend.service.vendor.VendorService;
import com.like.minded.backend.vo.vendor.VendorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
@CrossOrigin(origins = "*")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/create_vendor")
    public ResponseEntity<VendorResponse> createVendor(@RequestBody VendorCreationDto vendorCreationDto) {
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
}