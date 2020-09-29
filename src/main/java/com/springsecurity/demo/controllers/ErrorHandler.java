package com.springsecurity.demo.controllers;

import com.springsecurity.demo.exceptions.NotAllowedException;
import com.springsecurity.demo.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> genericErrorHandler() {
        return new ResponseEntity<>("Bad Request!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<?> NotAllowedErrorHandler() {
        return new ResponseEntity<>("Not Allowed Niggur!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> NoSuchElementExceptionHandler() {
        return new ResponseEntity<>("Not Found!!", HttpStatus.NOT_FOUND);
    }

}
