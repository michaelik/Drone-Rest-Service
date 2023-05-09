package com.droneservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InsufficientWeightException extends RuntimeException {
    public InsufficientWeightException (String message){super(message);}
}
