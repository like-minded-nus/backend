package com.like.minded.backend.vo;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T payload;
}
