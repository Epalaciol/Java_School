package com.school.exception;

public class SchoolRequestException extends RuntimeException {

    public SchoolRequestException(String message) {
        super(message);
    }

    public SchoolRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
