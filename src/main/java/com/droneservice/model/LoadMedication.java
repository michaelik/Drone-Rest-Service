package com.droneservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "drone_load_tbl")
public class LoadMedication {

    @Id
    @SequenceGenerator(
            name = "drone_load_id_seq",
            sequenceName = "drone_load_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drone_load_id_seq"
    )
    Integer trackId;
    @Column(
            name = "source",
            nullable = false
    )
    String source;
    @Column(
            name = "destination",
            nullable = false
    )
    String destination;
    @OneToOne(
            targetEntity = Drone.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "fk_serial_no",
            referencedColumnName = "serial_no"
    )
    Drone drone;
    @OneToOne(
            targetEntity = Medication.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "fk_code",
            referencedColumnName = "code",
            unique = true
    )
    Medication medication;
    @Column(
            nullable = false
    )
    LocalDateTime createdAt;

}
