package com.droneservice.service.implementation;

import com.droneservice.exception.DuplicateResourceException;
import com.droneservice.exception.ResourceNotFoundException;
import com.droneservice.model.Drone;
import com.droneservice.payload.request.DroneBarLevelRequest;
import com.droneservice.payload.request.DroneRequest;
import com.droneservice.payload.response.DroneBarLevelResponse;
import com.droneservice.repository.DroneRepository;
import com.droneservice.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public void addDrone(DroneRequest request) {
        boolean serialNumberExit = droneRepository
                .findBySerialNumber(request.getSerialNumber())
                .isPresent();
        if (serialNumberExit)throw new DuplicateResourceException("Drone Already Created");
        Drone drone = Drone.builder()
                .serialNumber(request.getSerialNumber())
                .model(request.getModel())
                .weight(request.getWeight())
                .battery(request.getBattery())
                .state(request.getState())
                .build();
        droneRepository.save(drone);
    }

    @Override
    public DroneBarLevelResponse getDroneBarLevel(DroneBarLevelRequest request) {
        Drone droneBattery = droneRepository
                .findBySerialNumber(request.getSerialNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Battery level details not found"));
        DecimalFormat decimalFormat = new DecimalFormat("#%");
        decimalFormat.format(droneBattery.getBattery());
        return new DroneBarLevelResponse(droneBattery.getSerialNumber(),
                droneBattery.getBattery(),
                LocalDateTime.now());
    }
}
