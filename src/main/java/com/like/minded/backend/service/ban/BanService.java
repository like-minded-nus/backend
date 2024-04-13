/* LikeMinded (C)2024 */
package com.like.minded.backend.service.ban;

import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import com.like.minded.backend.vo.BaseResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface BanService {

    public ResponseEntity<BaseResponse<Integer>> banUser(BanUserDto banUserDto)
            throws SQLException, IOException;

    public ResponseEntity<BaseResponse<List<GetBannedUsersDto>>> findBannedUsers()
            throws SQLException, IOException;

    public ResponseEntity<BaseResponse<Boolean>> findIsUserBanned(Integer userId)
            throws SQLException, IOException;
}
