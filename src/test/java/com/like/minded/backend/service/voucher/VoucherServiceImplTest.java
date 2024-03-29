package com.like.minded.backend.service.voucher;

import com.like.minded.backend.domain.voucher.Voucher;
import com.like.minded.backend.dto.voucher.VoucherCreationDto;
import com.like.minded.backend.exception.VoucherException;
import com.like.minded.backend.repository.voucher.VoucherRepository;
import com.like.minded.backend.vo.voucher.VoucherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {

    @Mock
    private VoucherRepository voucherRepository;

    @InjectMocks
    private VoucherServiceImpl voucherService;

    @Test
    void createVoucherSuccessfully() {
        VoucherCreationDto dto = new VoucherCreationDto("Summer Sale", LocalDate.now().plusDays(10), "10% off", true, 1);

        when(voucherRepository.existsByVoucherName(dto.getVoucherName())).thenReturn(false);
        when(voucherRepository.save(any(Voucher.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<VoucherResponse> response = voucherService.createVoucher(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully created voucher", Objects.requireNonNull(response.getBody()).getMessage());

        verify(voucherRepository).save(any(Voucher.class));
    }

    @Test
    void createVoucherFailsWhenNameExists() {
        VoucherCreationDto dto = new VoucherCreationDto("Summer Sale", LocalDate.now().plusDays(10), "10% off", true, 1);

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
}