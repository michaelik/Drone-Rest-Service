package com.droneservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "medical_delivery_tbl")
public class MedicationDelivery {

    @Id
    @SequenceGenerator(
            name = "medical_delivery_id_seq",
            sequenceName = "medical_delivery_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medical_delivery_id_seq"
    )
    Integer id;
    @OneToOne(
            targetEntity = LoadMedication.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "fk_trackId",
            referencedColumnName = "trackId",
            unique = true
    )
    LoadMedication loadMedication;
    @Column(
            nullable = false
    )
    LocalDateTime createdAt;
}
