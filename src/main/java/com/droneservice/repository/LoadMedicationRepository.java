package com.droneservice.repository;

import com.droneservice.model.LoadMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadMedicationRepository extends JpaRepository<LoadMedication, Integer> {

    @Query(value = "SELECT * from tbl_drone_load e where e.fk_code =:code", nativeQuery = true)
    LoadMedication findByCode(@Param("code") String code);

    @Query(value = "SELECT * from tbl_drone_load e where e.fk_serial_no =:serial", nativeQuery = true)
    LoadMedication findByDrone(@Param("serial") String serialNumber);
}
