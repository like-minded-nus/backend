/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.ban;

/* LikeMinded (C)2024 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBannedUsersDto {
    private Integer banId;
    private Integer userId;
    private String username;
    private String bannedReason;
}
