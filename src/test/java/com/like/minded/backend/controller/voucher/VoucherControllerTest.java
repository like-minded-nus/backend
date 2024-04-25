/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.voucher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.service.voucher.VoucherService;
import com.like.minded.backend.vo.voucher.VoucherResponse;
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
class VoucherControllerTest {

    @Mock private VoucherService voucherService;

    @InjectMocks private VoucherController voucherController;

    @Test
    void createVoucher() {
        VoucherCreationDto creationDto = new VoucherCreationDto();
        VoucherResponse expectedResponse = VoucherResponse.builder().build();
        when(voucherService.createVoucher(creationDto))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<VoucherResponse> response = voucherController.createVoucher(creationDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getAllVouchers() {
        List<Voucher> vouchers = Arrays.asList(new Voucher(), new Voucher());
        when(voucherService.getAllVouchers()).thenReturn(vouchers);

        ResponseEntity<List<Voucher>> response = voucherController.getAllVouchers();

        assertEquals(OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getAllVouchers_Empty() {
        when(voucherService.getAllVouchers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Voucher>> response = voucherController.getAllVouchers();

        assertEquals(NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getVoucherById() {
        Voucher voucher = new Voucher();
        when(voucherService.getVoucherById(1)).thenReturn(voucher);

        ResponseEntity<Voucher> response = voucherController.getVoucherById(1);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getVoucherById_NotFound() {
        when(voucherService.getVoucherById(1)).thenReturn(null);

        ResponseEntity<Voucher> response = voucherController.getVoucherById(1);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getVouchersByVendorId() {
        List<Voucher> vouchers = Arrays.asList(new Voucher(), new Voucher());
        when(voucherService.getVouchersByVendorId(1)).thenReturn(vouchers);

        ResponseEntity<List<Voucher>> response = voucherController.getVouchersByVendorId(1);

        assertEquals(OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getVouchersByVendorId_Empty() {
        when(voucherService.getVouchersByVendorId(1)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Voucher>> response = voucherController.getVouchersByVendorId(1);

        assertEquals(NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateVoucher() {
        VoucherCreationDto updatedDto = new VoucherCreationDto();
        VoucherResponse expectedResponse = VoucherResponse.builder().build();
        when(voucherService.updateVoucher(1, updatedDto))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<VoucherResponse> response = voucherController.updateVoucher(1, updatedDto);

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateVoucher_NotFound() {
        VoucherCreationDto updatedDto = new VoucherCreationDto();
        when(voucherService.updateVoucher(1, updatedDto))
                .thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<VoucherResponse> response = voucherController.updateVoucher(1, updatedDto);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteVoucher() {
        when(voucherService.deleteVoucher(1)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<VoucherResponse> response = voucherController.deleteVoucher(1);

        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void deleteVoucher_NotFound() {
        when(voucherService.deleteVoucher(1)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<VoucherResponse> response = voucherController.deleteVoucher(1);

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
