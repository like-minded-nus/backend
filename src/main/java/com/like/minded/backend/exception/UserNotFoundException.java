/* LikeMinded (C)2024 */
package com.like.minded.backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
