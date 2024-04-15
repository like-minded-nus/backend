/* LikeMinded (C)2024 */
package com.like.minded.backend.command.ban;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.service.ban.BanService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BanUserCommandTest {

    @Mock BanService banService;

    @InjectMocks BanUserCommand banUserCommand;

    BanUserDto banUserDto;

    @BeforeEach
    void setUp() {
        banUserDto = new BanUserDto();
        banUserDto.setUserId(9999);
        banUserDto.setBannedReason("Fake profile");

        banUserCommand = new BanUserCommand(banService, banUserDto);
    }

    @Test
    void executeTest() {
        // When
        banUserCommand.execute();

        // Then
        verify(banService, times(1)).banUser(banUserDto);
    }
}
