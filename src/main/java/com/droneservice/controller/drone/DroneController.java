package com.droneservice.controller.drone;

import com.droneservice.payload.request.DroneRequest;
import com.droneservice.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/drone")
@Validated
public class DroneController {

    private final DroneService droneService;

    @PostMapping(path = "/register", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<String> addDrone(@Valid @RequestBody DroneRequest request) {
        droneService.addDrone(request);
        return new ResponseEntity<>("Drone created successfully", HttpStatus.CREATED);
    }


}
