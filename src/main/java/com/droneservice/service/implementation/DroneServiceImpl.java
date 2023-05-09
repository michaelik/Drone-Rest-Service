package com.droneservice.service.implementation;

import com.droneservice.exception.DuplicateResourceException;
import com.droneservice.exception.InsufficientWeightException;
import com.droneservice.exception.LowBatteryException;
import com.droneservice.exception.ResourceNotFoundException;
import com.droneservice.model.Drone;
import com.droneservice.model.LoadMedication;
import com.droneservice.model.Medication;
import com.droneservice.model.MedicationDelivery;
import com.droneservice.payload.request.DeliveryStatusRequest;
import com.droneservice.payload.request.DroneBarLevelRequest;
import com.droneservice.payload.request.DroneRequest;
import com.droneservice.payload.request.LoadDroneRequest;
import com.droneservice.payload.response.DeliveryStatusResponse;
import com.droneservice.payload.response.DroneBarLevelResponse;
import com.droneservice.payload.response.IdleDronesResponse;
import com.droneservice.payload.response.MedicationDetailsResponse;
import com.droneservice.repository.DroneRepository;
import com.droneservice.repository.LoadMedicationRepository;
import com.droneservice.repository.MedicationRepository;
import com.droneservice.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final LoadMedicationRepository loadDroneRepository;

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

    @Override
    public IdleDronesResponse getAvailableDrone() {
        String state = "IDLE";
        List<Drone> drones = droneRepository.findAllByState(state);
        return new IdleDronesResponse(drones,
                java.time.LocalDateTime.now()
                );
    }

    @Override
    public void loadDrone(LoadDroneRequest request) {
        Drone drone = droneRepository
                .findBySerialNumber(request.getSerialNumber())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "drone with serial number [%s] not found".formatted(
                                request.getSerialNumber()
                        )
                ));
        Medication medication = medicationRepository
                .findByCode(request.getCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "medication with code [%s] not found".formatted(
                                request.getCode()
                        )
                ));
        boolean isMedicationLoaded = loadDroneRepository
                .findByCode(request.getCode())
                .isPresent();
        if (isMedicationLoaded) throw new DuplicateResourceException(
                "medication with code [%s] already loaded".formatted(
                        request.getCode()
                )
        );
        if (drone.getWeight() < medication.getWeight()) {
            throw new InsufficientWeightException(
                    "The Drone cannot load more than it weight limit"
            );
        }

        if( drone.getBattery().compareTo(new BigDecimal("0.25")) < 0){
            throw new LowBatteryException(
                    "The Drone cannot be loaded, battery is below 25%"
            );
        }

        LoadMedication loadMedication = LoadMedication.builder()
                .source(request.getSource())
                .destination(request.getDestination())
                .drone(drone)
                .medication(medication)
                .createdAt(LocalDateTime.now())
                .build();
        loadDroneRepository.save(loadMedication);
        drone.setState("LOADED");
        droneRepository.save(drone);
    }

    @Override
    public MedicationDetailsResponse getLoadedMedDetail(String request) {
        LoadMedication loadMedication = loadDroneRepository
                .findByDrone(request)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "medication with serial number [%s] not found".formatted(
                                request
                        )
                ));
        return new MedicationDetailsResponse(
                loadMedication.getDrone().getSerialNumber(),
                loadMedication.getMedication(),
                LocalDateTime.now()
        );
    }

    @Override
    public DeliveryStatusResponse getDeliveryStatus(DeliveryStatusRequest request) {
        LoadMedication loadMedication = loadDroneRepository
                .findByDrone(request.getSerialNumber())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "medication with serial number [%s] not found".formatted(
                                request
                        )
                ));
        MedicationDelivery medicationDelivery = MedicationDelivery.builder()
                .loadMedication(loadMedication)
                .createdAt(LocalDateTime.now())
                .build();
        droneRepository.setUpdateState("DELIVERED", loadMedication.getDrone().getSerialNumber());
        return new DeliveryStatusResponse(
                loadMedication.getDrone().getState(),
                medicationDelivery.getCreatedAt()
        );
    }
}
