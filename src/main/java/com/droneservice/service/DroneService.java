package com.droneservice.service;

import com.droneservice.payload.request.DroneRequest;
import org.springframework.stereotype.Component;

@Component
public interface DroneService {

    void addDrone(DroneRequest request);

}
