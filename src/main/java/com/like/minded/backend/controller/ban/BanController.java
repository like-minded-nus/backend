/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.ban;

import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import com.like.minded.backend.service.ban.BanService;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ban")
@CrossOrigin
public class BanController {
    @Autowired private BanService banService;

    @PostMapping
    public ResponseEntity<BaseResponse<Integer>> banUser(@RequestBody BanUserDto banUserDto) {
        return banService.banUser(banUserDto);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<GetBannedUsersDto>>> getBannedUsers() {
        return banService.findBannedUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<Boolean>> getIsUserBanned(@PathVariable Integer userId) {
        return banService.findIsUserBanned(userId);
    }
}
