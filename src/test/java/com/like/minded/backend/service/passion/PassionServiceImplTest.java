/* LikeMinded (C)2024 */
package com.like.minded.backend.service.passion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.like.minded.backend.domain.passion.Passion;
import com.like.minded.backend.repository.passion.PassionRepository;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.passion.PassionResponse;
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
class PassionServiceImplTest {

    @Mock private PassionRepository passionRepository;

    @InjectMocks private PassionServiceImpl passionService;

    @Test
    void getPassionsShouldReturnListOfPassions() {
        // Arrange
        Passion passion1 = new Passion(1, "Hiking");
        Passion passion2 = new Passion(2, "Running");
        List<Passion> passions = Arrays.asList(passion1, passion2);
        when(passionRepository.findAll()).thenReturn(passions);

        // Act
        ResponseEntity<BaseResponse<PassionResponse>> responseEntity = passionService.getPassions();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(
                2,
                Objects.requireNonNull(responseEntity.getBody())
                        .getPayload()
                        .getPassionList()
                        .size());
        assertEquals("Successfully retrieved passions", responseEntity.getBody().getMessage());

        verify(passionRepository).findAll();
    }

    @Test
    void getPassionsByProfileId() {
        List<Passion> passions = Arrays.asList(new Passion(1, "Reading"), new Passion(2, "Hiking"));
        when(passionRepository.findPassionsByProfileId(1)).thenReturn(passions);

        ResponseEntity<BaseResponse<PassionResponse>> response =
                passionService.getPassionsByProfileId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getPayload().getPassionList().size());
        assertEquals("Successfully retrieved passions", response.getBody().getMessage());
        verify(passionRepository).findPassionsByProfileId(1);
    }

    @Test
    void getPassionById() {
        Passion passion = new Passion(1, "Cycling");
        when(passionRepository.findById(1)).thenReturn(Optional.of(passion));

        ResponseEntity<BaseResponse<PassionResponse>> response = passionService.getPassionById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(
                "Cycling",
                response.getBody().getPayload().getPassionList().get(0).getPassionName());
        assertEquals("Successfully retrieved passion", response.getBody().getMessage());
        verify(passionRepository).findById(1);
    }

    @Test
    void getPassionByIdNotFound() {
        when(passionRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<BaseResponse<PassionResponse>> response = passionService.getPassionById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Passion not found", response.getBody().getMessage());
    }

    @Test
    void getPassionIdsByName() {
        List<Passion> passions =
                Arrays.asList(new Passion(1, "Swimming"), new Passion(2, "Running"));
        when(passionRepository.findByPassionNameIn(Arrays.asList("Swimming", "Running")))
                .thenReturn(passions);

        ResponseEntity<BaseResponse<List<Integer>>> response =
                passionService.getPassionIdsByName(Arrays.asList("Swimming", "Running"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Arrays.asList(1, 2), response.getBody().getPayload());
        assertEquals(
                "Successfully retrieved passion IDs by names", response.getBody().getMessage());
        verify(passionRepository).findByPassionNameIn(Arrays.asList("Swimming", "Running"));
    }
}
