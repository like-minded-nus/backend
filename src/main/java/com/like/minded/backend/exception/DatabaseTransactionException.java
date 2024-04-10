/* LikeMinded (C)2024 */
package com.like.minded.backend.exception;

public class DatabaseTransactionException extends RuntimeException {
    public DatabaseTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
