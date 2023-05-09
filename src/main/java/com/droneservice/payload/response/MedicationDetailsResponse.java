package com.droneservice.payload.response;

import com.droneservice.model.Medication;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicationDetailsResponse {

    String serialNumber;
    Medication medication;
    LocalDateTime createdAt;
}
