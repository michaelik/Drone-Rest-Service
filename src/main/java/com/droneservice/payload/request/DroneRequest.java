package com.droneservice.payload.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DroneRequest {

    @NotBlank(message = "serial number should not be empty")
    String serialNumber;
    @NotBlank(message = "model should not be empty")
    String model;
    @Min(value = 1, message = "weight can not be zero(0)")
    double weight;
    @DecimalMin(value = "0", inclusive = false, message = "battery level can not be zero(0)")
    BigDecimal battery;
    @NotBlank(message = "state should not be empty")
    String state;
}
