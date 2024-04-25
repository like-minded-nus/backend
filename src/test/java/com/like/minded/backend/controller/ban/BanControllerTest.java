/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.ban;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.like.minded.backend.command.Command;
import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.service.ban.BanService;
import com.like.minded.backend.vo.BaseResponse;
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
class BanControllerTest {

    @Mock private BanService banService;

    @Mock private Command command;

    @InjectMocks private BanController banController;

    @Test
    void testSetCommand() {
        // Arrange
        Command newCommand = mock(Command.class);

        // Act
        banController.setCommand(newCommand);

        // Assert
        assertNotNull(banController);
    }

    @Test
    void testExecuteCommand_Success() throws Exception {
        // Arrange
        doNothing().when(command).execute();

        // Act
        ResponseEntity<?> response = banController.executeCommand();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testExecuteCommand_Failure_DatabaseException() throws Exception {
        // Arrange
        doThrow(new DatabaseTransactionException("Database error")).when(command).execute();

        // Act
        ResponseEntity<?> response = banController.executeCommand();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testExecuteCommand_Failure_OtherException() throws Exception {
        // Arrange
        doThrow(new RuntimeException("Other error")).when(command).execute();

        // Act
        ResponseEntity<?> response = banController.executeCommand();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testBanUser() {
        // Arrange
        BanUserDto banUserDto = new BanUserDto();
        banUserDto.setUserId(1);

        // Act
        ResponseEntity<BaseResponse<Integer>> response = banController.banUser(banUserDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetBannedUsers() {
        // Arrange
        List<GetBannedUsersDto> bannedUsers = Arrays.asList(new GetBannedUsersDto());

        when(banService.findBannedUsers())
                .thenReturn(
                        ResponseEntity.status(HttpStatus.OK)
                                .body(
                                        BaseResponse.<List<GetBannedUsersDto>>builder()
                                                .status(HttpStatus.OK.value())
                                                .message("Successfully retrieved banned users")
                                                .payload(bannedUsers)
                                                .build()));

        // Act
        ResponseEntity<BaseResponse<List<GetBannedUsersDto>>> response =
                banController.getBannedUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()).getPayload());
        assertEquals(1, response.getBody().getPayload().size());
    }
}
