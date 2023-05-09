package com.droneservice.service;

import com.droneservice.payload.request.DroneBarLevelRequest;
import com.droneservice.payload.request.DroneRequest;
import com.droneservice.payload.response.DroneBarLevelResponse;
import org.springframework.stereotype.Component;

@Component
public interface DroneService {

    void addDrone(DroneRequest request);
    DroneBarLevelResponse getDroneBarLevel(DroneBarLevelRequest request);

}
