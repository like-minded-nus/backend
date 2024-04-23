/* LikeMinded (C)2024 */
package com.like.minded.backend.service.voucher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.domain.voucher.VoucherType;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.exception.VoucherException;
import com.like.minded.backend.repository.voucher.VoucherRepository;
import com.like.minded.backend.repository.voucher.VoucherTypeRepository;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {

    @Mock private VoucherRepository voucherRepository;

    @Mock private VoucherTypeRepository voucherTypeRepository;

    @InjectMocks private VoucherServiceImpl voucherService;

    @Test
    void createVoucherSuccessfully() {
        VoucherCreationDto dto =
                new VoucherCreationDto(
                        "Fun Time One Time", LocalDate.now().plusDays(10), 1, 3, true, 1);

        VoucherType mockVoucherType = new VoucherType(2, "Discount");
        when(voucherRepository.existsByVoucherName(dto.getVoucherName())).thenReturn(false);
        when(voucherTypeRepository.findByVoucherType(any(Integer.class)))
                .thenReturn(mockVoucherType);
        when(voucherRepository.save(any(Voucher.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<VoucherResponse> response = voucherService.createVoucher(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                "Successfully created voucher",
                Objects.requireNonNull(response.getBody()).getMessage());

        verify(voucherRepository).save(any(Voucher.class));
    }

    @Test
    void createVoucherFailsWhenNameExists() {
        VoucherCreationDto dto =
                new VoucherCreationDto(
                        "Fun Time One Time", LocalDate.now().plusDays(10), 1, 3, true, 1);

        when(voucherRepository.existsByVoucherName(dto.getVoucherName())).thenReturn(true);

        assertThrows(VoucherException.class, () -> voucherService.createVoucher(dto));
    }

    @Test
    void getVoucherByIdShouldReturnVoucher() {
        Integer voucherId = 1;
        Voucher voucher = new Voucher();
        voucher.setVoucherId(voucherId);

        when(voucherRepository.findById(voucherId)).thenReturn(Optional.of(voucher));

        Voucher foundVoucher = voucherService.getVoucherById(voucherId);

        assertNotNull(foundVoucher);
        assertEquals(voucherId, foundVoucher.getVoucherId());

        verify(voucherRepository).findById(voucherId);
    }

    @Test
    void getAllVouchersShouldReturnList() {
        Voucher voucher1 = new Voucher();
        Voucher voucher2 = new Voucher();
        List<Voucher> vouchers = Arrays.asList(voucher1, voucher2);

        when(voucherRepository.findAll()).thenReturn(vouchers);

        List<Voucher> foundVouchers = voucherService.getAllVouchers();

        assertFalse(foundVouchers.isEmpty());
        assertEquals(2, foundVouchers.size());

        verify(voucherRepository).findAll();
    }

    @Test
    void updateVoucher() {
        Integer voucherId = 1;
        Voucher existingVoucher = new Voucher();
        VoucherType voucherType = new VoucherType();
        VoucherCreationDto updatedVoucherDto = new VoucherCreationDto();
        updatedVoucherDto.setVoucherType(1);
        updatedVoucherDto.setVoucherName("Fun Time One Time");
        updatedVoucherDto.setVoucherAmount(10);

        when(voucherRepository.findById(voucherId)).thenReturn(Optional.of(existingVoucher));
        when(voucherTypeRepository.findByVoucherType(updatedVoucherDto.getVoucherType()))
                .thenReturn(voucherType);
        when(voucherRepository.save(any(Voucher.class))).thenReturn(existingVoucher);

        ResponseEntity<VoucherResponse> response =
                voucherService.updateVoucher(voucherId, updatedVoucherDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully updated voucher", response.getBody().getMessage());
        verify(voucherRepository).save(existingVoucher);
    }

    @Test
    void updateVoucherNotFound() {
        Integer voucherId = 1;
        VoucherCreationDto updatedVoucherDto = new VoucherCreationDto();
        when(voucherRepository.findById(voucherId)).thenReturn(Optional.empty());

        ResponseEntity<VoucherResponse> response =
                voucherService.updateVoucher(voucherId, updatedVoucherDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getVouchersByVendorId() {
        Integer vendorId = 1;
        List<Voucher> vouchers = Arrays.asList(new Voucher(), new Voucher());
        when(voucherRepository.findByVendorId(vendorId)).thenReturn(vouchers);

        List<Voucher> foundVouchers = voucherService.getVouchersByVendorId(vendorId);

        assertEquals(2, foundVouchers.size());
        verify(voucherRepository).findByVendorId(vendorId);
    }

    @Test
    void deleteVoucher() {
        Integer voucherId = 1;
        Voucher existingVoucher = new Voucher();

        when(voucherRepository.findById(voucherId)).thenReturn(Optional.of(existingVoucher));
        doNothing().when(voucherRepository).deleteById(voucherId);

        ResponseEntity<VoucherResponse> response = voucherService.deleteVoucher(voucherId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Voucher deleted successfully", response.getBody().getMessage());
        verify(voucherRepository).deleteById(voucherId);
    }

    @Test
    void deleteVoucherNotFound() {
        Integer voucherId = 1;
        when(voucherRepository.findById(voucherId)).thenReturn(Optional.empty());

        ResponseEntity<VoucherResponse> response = voucherService.deleteVoucher(voucherId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
