package com.droneservice.repository;

import com.droneservice.model.LoadMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoadMedicationRepository extends JpaRepository<LoadMedication, Integer> {

    @Query(value = "SELECT * from drone_load_tbl e where e.fk_code =:code", nativeQuery = true)
    Optional<LoadMedication> findByCode(@Param("code") String code);

    @Query(value = "SELECT * from drone_load_tbl e where e.fk_serial_no =:serial", nativeQuery = true)
    LoadMedication findByDrone(@Param("serial") String serialNumber);
}
