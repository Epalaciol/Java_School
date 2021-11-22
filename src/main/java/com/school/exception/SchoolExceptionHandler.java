package com.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SchoolExceptionHandler {

    @ExceptionHandler(value = {SchoolRequestException.class})
    public ResponseEntity<Object> handleSchoolRequestException(SchoolRequestException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        SchoolException schoolException = new SchoolException(
                e.getMessage(),
                e,
                badRequest
        );

        return  new ResponseEntity<>(schoolException, badRequest);
    }
}
