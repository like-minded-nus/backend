/* LikeMinded (C)2024 */
package com.like.minded.backend.command.ban;

import com.like.minded.backend.command.Command;
import com.like.minded.backend.dto.ban.BanUserDto;
import com.like.minded.backend.service.ban.BanService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BanUserCommand implements Command {
    BanService banService;
    BanUserDto banUserDto;

    @Override
    public void execute() {
        banService.banUser(banUserDto);
    }
}
