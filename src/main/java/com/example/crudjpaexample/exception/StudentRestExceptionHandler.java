package com.example.crudjpaexample.exception;

import com.example.crudjpaexample.model.StudentErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    ////////////////////////////////////////////////////////////////////

    /**
     * @param e
     * @return
     */
    @ExceptionHandler
    ResponseEntity<?> handleException(StudentNotFoundException e) {
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        logger.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    ////////////////////////////////////////////////////////////////////

    /**
     * @param e
     * @return
     */
    @ExceptionHandler
    ResponseEntity<?> handleException(Exception e) {
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        logger.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
