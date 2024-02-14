package com.like.minded.backend.vo.passion;

import com.like.minded.backend.domain.passion.Passion;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class PassionResponse {
    private Integer status;
    private String message;
    private List<Passion> passionList;
}
