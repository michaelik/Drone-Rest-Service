package com.droneservice.payload.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoadDroneRequest {
    @NotBlank(message = "serial number should not be empty")
    String serialNumber;
    @NotBlank(message = "code should not be empty")
    String code;
    @NotBlank(message = "source should not be empty")
    String source;
    @NotBlank(message = "destination should not be empty")
    String destination;
}
