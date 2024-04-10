/* LikeMinded (C)2024 */
package com.like.minded.backend.vo;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T payload;

    private BaseResponse(Builder<T> builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.payload = builder.payload;
    }

    public static class Builder<T> {
        private Integer status;
        private String message;
        private T payload;

        public Builder<T> status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

        public BaseResponse<T> build() {
            return new BaseResponse<>(this);
        }
    }
}
