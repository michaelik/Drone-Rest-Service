package com.droneservice.controller.drone;

import com.droneservice.payload.request.DeliveryStatusRequest;
import com.droneservice.payload.request.DroneBarLevelRequest;
import com.droneservice.payload.request.DroneRequest;
import com.droneservice.payload.request.LoadDroneRequest;
import com.droneservice.payload.response.DeliveryStatusResponse;
import com.droneservice.payload.response.DroneBarLevelResponse;
import com.droneservice.payload.response.DroneResponse;
import com.droneservice.payload.response.IdleDronesResponse;
import com.droneservice.payload.response.LoadDroneResponse;
import com.droneservice.payload.response.MedicationDetailsResponse;
import com.droneservice.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/v1/drone")
@Validated
public class DroneController {

    private final DroneService droneService;

    @PostMapping(path = "/register", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<DroneResponse> addDrone(@Valid
                                               @RequestBody()
                                               DroneRequest request)
    {
        droneService.addDrone(request);
        return new ResponseEntity<>(new DroneResponse("drone created successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping(path ="/battery", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<DroneBarLevelResponse> checkDroneBatteryLevel(@Valid
                                                                       @RequestBody()
                                                                       DroneBarLevelRequest request)
    {
        DroneBarLevelResponse droneBarLevel = droneService.getDroneBarLevel(request);
        return new ResponseEntity<>(droneBarLevel, HttpStatus.OK);
    }

    @GetMapping(path ="/available", produces = "application/json")
    public ResponseEntity<IdleDronesResponse> getIdleDrones()
    {
        IdleDronesResponse idleDrones = droneService.getAvailableDrone();
        return new ResponseEntity<>(idleDrones, HttpStatus.OK);
    }

    @PostMapping(path = "/load", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<LoadDroneResponse> loadDrone(@Valid
                                                          @RequestBody()
                                                          LoadDroneRequest request)
    {
        droneService.loadDrone(request);
        return new ResponseEntity<>(new LoadDroneResponse("drone loaded successfully"),
                HttpStatus.CREATED);
    }

    @GetMapping(path = "/details/{serialNumber}", produces = "application/json")
    public ResponseEntity<MedicationDetailsResponse> getLoadedMedicationDetail(@PathVariable("serialNumber")
                                                                                   String request)
    {
        MedicationDetailsResponse medicationDetailsResponse = droneService.getLoadedMedDetail(request);
        return new ResponseEntity<>(medicationDetailsResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/deliver", consumes = {MediaType.ALL_VALUE}, produces = "application/json")
    public ResponseEntity<DeliveryStatusResponse> DroneDeliveryStatus(@Valid
                                                               @RequestBody()
                                                               DeliveryStatusRequest request)
    {
        DeliveryStatusResponse status = droneService.getDeliveryStatus(request);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
