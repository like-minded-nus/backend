/* LikeMinded (C)2024 */
package com.like.minded.backend.vo.passion;

import com.like.minded.backend.domain.passion.Passion;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassionResponse {
    private List<Passion> passionList;
}
