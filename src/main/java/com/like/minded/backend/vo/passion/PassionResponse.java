package com.like.minded.backend.vo.passion;

import com.like.minded.backend.domain.passion.Passion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassionResponse {
    private List<Passion> passionList;
}
