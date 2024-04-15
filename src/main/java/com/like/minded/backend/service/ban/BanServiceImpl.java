/* LikeMinded (C)2024 */
package com.like.minded.backend.service.ban;

import com.like.minded.backend.domain.ban.Ban;
import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.repository.ban.BanRepository;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BanServiceImpl implements BanService {

    private final ModelMapper modelMapper;
    private final BanRepository banRepository;

    @Override
    public ResponseEntity<BaseResponse<Integer>> banUser(BanUserDto banUserDto)
            throws SQLException, IOException {

        Ban newBan =
                Ban.builder()
                        .userId(banUserDto.getUserId())
                        .bannedReason(banUserDto.getBannedReason())
                        .build();

        try {
            banRepository.save(newBan);
        } catch (Exception e) {
            throw new DatabaseTransactionException("Error saving ban into Database", e);
        }

        BaseResponse<Integer> response =
                BaseResponse.<Integer>builder()
                        .status(200)
                        .message("Successfully created ban")
                        .payload(banUserDto.getUserId())
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<BaseResponse<List<GetBannedUsersDto>>> findBannedUsers()
            throws SQLException, IOException {

        List<GetBannedUsersDto> getBannedUsersDtoList = banRepository.findBannedUsers();

        BaseResponse<List<GetBannedUsersDto>> response =
                BaseResponse.<List<GetBannedUsersDto>>builder()
                        .status(200)
                        .message("Successfully retrieved banned users")
                        .payload(getBannedUsersDtoList)
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public boolean findIsUserBanned(Integer userId) {
        List<Ban> ban = banRepository.findByUserId(userId);

        //        boolean result = !ban.isEmpty();

        //        BaseResponse<Boolean> response =
        //                BaseResponse.<Boolean>builder()
        //                        .status(200)
        //                        .message("Successfully checks user ban status")
        //                        .payload(result)
        //                        .build();

        //        return ResponseEntity.status(HttpStatus.OK).body(response);
        return !ban.isEmpty();
    }
}
