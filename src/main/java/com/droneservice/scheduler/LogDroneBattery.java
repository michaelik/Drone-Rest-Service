package com.droneservice.scheduler;

import com.droneservice.model.Drone;
import com.droneservice.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@SpringBootApplication
//@EnableScheduling
//@EnableAsync
public class LogDroneBattery {

    private final DroneRepository droneRepository;
    static final Logger log = LoggerFactory.getLogger(LogDroneBattery.class);

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTaskAsync() {
        try {
            List<Drone> arrDroneBatteryLevels = droneRepository.findAll();
            DecimalFormat decFormat = new DecimalFormat("#%");

            for (Drone arrDroneBatteryLevel : arrDroneBatteryLevels) {
                log.info("Serial Number  "
                        + arrDroneBatteryLevel.getSerialNumber()
                        + "  Battery Level is "
                        + decFormat.format(arrDroneBatteryLevel.getBattery()));
            }

            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            log.error("Error in the scheduled task: " + ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }
    }
}
