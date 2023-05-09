package com.droneservice.service;

import com.droneservice.payload.request.DeliveryStatusRequest;
import com.droneservice.payload.request.DroneBarLevelRequest;
import com.droneservice.payload.request.DroneRequest;
import com.droneservice.payload.request.LoadDroneRequest;
import com.droneservice.payload.response.*;
import org.springframework.stereotype.Component;

@Component
public interface DroneService {

    void addDrone(DroneRequest request);
    DroneBarLevelResponse getDroneBarLevel(DroneBarLevelRequest request);
    IdleDronesResponse getAvailableDrone();
    void loadDrone(LoadDroneRequest request);
    MedicationDetailsResponse getLoadedMedDetail(String request);
    DeliveryStatusResponse getDeliveryStatus(DeliveryStatusRequest request);
}
