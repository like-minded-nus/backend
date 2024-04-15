/* LikeMinded (C)2024 */
package com.like.minded.backend.service.ban;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.domain.ban.Ban;
import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import com.like.minded.backend.repository.ban.BanRepository;
import com.like.minded.backend.repository.report.ReportRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BanServiceImplTest {

    @Mock private BanRepository banRepository;

    @Mock private ReportRepository reportRepository;

    @InjectMocks private BanServiceImpl banService;

    @Test
    void banUser() {
        BanUserDto banUserDto = new BanUserDto(1, 1, "Fake profile");
        Ban ban = new Ban(1, 1, "Fake profile");
        when(banRepository.save(any(Ban.class))).thenReturn(ban);
        doNothing().when(reportRepository).deleteById(anyInt());

        ResponseEntity<BaseResponse<Integer>> response = banService.banUser(banUserDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Integer.valueOf(1), response.getBody().getPayload());
        verify(banRepository).save(any(Ban.class));
        verify(reportRepository).deleteById(banUserDto.getId());
    }

    @Test
    void findBannedUsers() {
        List<GetBannedUsersDto> bannedUsers =
                Arrays.asList(new GetBannedUsersDto(1, 1, "testUser", "Fake profile"));
        when(banRepository.findBannedUsers()).thenReturn(bannedUsers);

        ResponseEntity<BaseResponse<List<GetBannedUsersDto>>> response =
                banService.findBannedUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getPayload().size());
        assertEquals("Fake profile", response.getBody().getPayload().get(0).getBannedReason());
        verify(banRepository).findBannedUsers();
    }

    @Test
    void findIsUserBanned() {
        List<Ban> bans = Arrays.asList(new Ban(1, 1, "Fake profile"));
        when(banRepository.findByUserId(anyInt())).thenReturn(bans);

        ResponseEntity<BaseResponse<Boolean>> response = banService.findIsUserBanned(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().getPayload());
        verify(banRepository).findByUserId(1);
    }
}
