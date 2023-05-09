package com.droneservice.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DroneBarLevelResponse {

    String serialNumber;
    BigDecimal battery;
    LocalDateTime timestamp;
}
