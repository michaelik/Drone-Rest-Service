package com.droneservice.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DroneBarLevelRequest {

    @NotBlank(message = "serial number should not be empty")
    String serialNumber;
}
