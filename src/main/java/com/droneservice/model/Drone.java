package com.droneservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "drone_tbl")
public class Drone {

    @Id
    @Column(
            name = "serial_no",
            nullable = false
    )
    String serialNumber;
    @Column(
            name = "model",
            nullable = false
    )
    String model;
    @Column(
            name = "weight",
            nullable = false
    )
    double weight;
    @Column(
            name = "battery",
            nullable = false
    )
    BigDecimal battery;
    @Column(
            name = "state",
            nullable = false
    )
    String state;
}
