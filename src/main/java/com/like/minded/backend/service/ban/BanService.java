/* LikeMinded (C)2024 */
package com.like.minded.backend.service.ban;

import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface BanService {

    ResponseEntity<BaseResponse<Integer>> banUser(BanUserDto banUserDto);

    ResponseEntity<BaseResponse<List<GetBannedUsersDto>>> findBannedUsers();

    ResponseEntity<BaseResponse<Boolean>> findIsUserBanned(Integer userId);
}
