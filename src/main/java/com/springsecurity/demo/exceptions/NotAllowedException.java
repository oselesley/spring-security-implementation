package com.springsecurity.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotAllowedException extends Exception {
    public NotAllowedException() {
    }

    public NotAllowedException(String message) {
        super(message);
    }
}
