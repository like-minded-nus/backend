/* LikeMinded (C)2024 */
package com.like.minded.backend.controller.ban;

import com.like.minded.backend.command.Command;
import com.like.minded.backend.command.ban.BanUserCommand;
import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.dto.ban.GetBannedUsersDto;
import com.like.minded.backend.exception.DatabaseTransactionException;
import com.like.minded.backend.service.ban.BanService;
import com.like.minded.backend.vo.BaseResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/ban")
@CrossOrigin
public class BanController {
    @Autowired private BanService banService;

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public ResponseEntity<?> executeCommand() {
        try {
            // Execute the encapsulated command
            command.execute();

            // Log the successful execution
            log.info("Command executed successfully");

            // Build a successful response
            return ResponseEntity.ok()
                    .body(
                            BaseResponse.builder()
                                    .status(HttpStatus.OK.value())
                                    .message("Command executed successfully")
                                    .payload(null)
                                    .build());
        } catch (DatabaseTransactionException e) {
            // Log the exception if there is a database transaction issue
            log.error("Database transaction failed: " + e.getMessage(), e);

            // Return a ResponseEntity with a server error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            BaseResponse.builder()
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .message("Failed to execute command: Database error")
                                    .payload(null)
                                    .build());
        } catch (Exception e) {
            // Log any other exceptions
            log.error("Error during command execution: " + e.getMessage(), e);

            // Return a generic server error ResponseEntity
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            BaseResponse.builder()
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .message("Failed to execute command")
                                    .payload(null)
                                    .build());
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Integer>> banUser(@RequestBody BanUserDto banUserDto) {
        Command banCommand = new BanUserCommand(banService, banUserDto);
        setCommand(banCommand);
        return (ResponseEntity<BaseResponse<Integer>>) executeCommand();
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<GetBannedUsersDto>>> getBannedUsers() {
        return banService.findBannedUsers();
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<BaseResponse<Boolean>> getIsUserBanned(@PathVariable Integer userId) {
//        return banService.findIsUserBanned(userId);
//    }
}
