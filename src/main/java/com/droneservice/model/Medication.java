package com.droneservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "medication_tbl")
public class Medication {

    @Id
    @Column(
            name = "code",
            nullable = false
    )
    String code;
    @Column(
            name = "name",
            nullable = false
    )
    String name;
    @Column(
            name = "weight",
            nullable = false
    )
    double weight;
    @Column(
            name = "image",
            nullable = false
    )
    String image;
}
