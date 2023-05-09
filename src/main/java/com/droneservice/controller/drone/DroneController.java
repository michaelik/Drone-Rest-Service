package com.droneservice.controller.drone;

import com.droneservice.payload.request.DroneBarLevelRequest;
import com.droneservice.payload.request.DroneRequest;
import com.droneservice.payload.request.LoadDroneRequest;
import com.droneservice.payload.response.DroneBarLevelResponse;
import com.droneservice.payload.response.IdleDronesResponse;
import com.droneservice.payload.response.LoadDroneResponse;
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
    public ResponseEntity<String> addDrone(@Valid
                                               @RequestBody(required = true)
                                               DroneRequest request)
    {
        droneService.addDrone(request);
        return new ResponseEntity<>("Drone created successfully", HttpStatus.CREATED);
    }

    @GetMapping(path ="/battery", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<DroneBarLevelResponse> checkDroneBattery(@Valid
                                                                       @RequestBody(required = true)
                                                                       DroneBarLevelRequest request)
    {
        DroneBarLevelResponse droneBarLevel = droneService.getDroneBarLevel(request);
        return new ResponseEntity<>(droneBarLevel, HttpStatus.OK);
    }

    @GetMapping(path ="/available", produces = "application/json")
    public ResponseEntity<IdleDronesResponse> checkDroneBattery()
    {
        IdleDronesResponse idleDrones = droneService.getAvailableDrone();
        return new ResponseEntity<>(idleDrones, HttpStatus.OK);
    }

    @PostMapping(path = "/load", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<LoadDroneResponse> addDrone(@Valid
                                                          @RequestBody(required = true)
                                                          LoadDroneRequest request)
    {
        droneService.loadDrone(request);
        return new ResponseEntity<>(new LoadDroneResponse("drone loaded successfully"), HttpStatus.CREATED);
    }

}
