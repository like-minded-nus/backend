/* LikeMinded (C)2024 */
package com.like.minded.backend.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkMessageAsReadDto {
    private Integer messageId;
}
