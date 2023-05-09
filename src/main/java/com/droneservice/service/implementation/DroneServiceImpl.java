package com.droneservice.service.implementation;

import com.droneservice.exception.DuplicateResourceException;
import com.droneservice.model.Drone;
import com.droneservice.payload.request.DroneRequest;
import com.droneservice.repository.DroneRepository;
import com.droneservice.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
