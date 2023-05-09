package com.droneservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LowBatteryException extends RuntimeException {
    public LowBatteryException (String message){super(message);}
}
