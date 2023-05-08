package com.droneservice.controller.drone;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1")
@Validated
public class DroneController {
    @GetMapping(path = "/user", produces = "application/json")
    public ResponseEntity<String> userAccess() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
