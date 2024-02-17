package com.like.minded.backend.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T payload;
}
