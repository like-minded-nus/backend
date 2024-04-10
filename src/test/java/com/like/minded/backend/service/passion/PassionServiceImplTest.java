/* LikeMinded (C)2024 */
package com.like.minded.backend.service.passion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.like.minded.backend.domain.passion.Passion;
import com.like.minded.backend.repository.passion.PassionRepository;
import com.like.minded.backend.vo.BaseResponse;
import com.like.minded.backend.vo.passion.PassionResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
}
