package com.droneservice.repository;

import com.droneservice.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    @Query(value = "SELECT e from Drone e where e.serialNumber =:serial ")
    Optional<Drone> findBySerialNumber (@Param("serial") String serialNumber);

    List<Drone> findAllByState (@Param("drone_state") String state);

    @Modifying
    @Transactional
    @Query(value = "update Drone d set d.state =:state where d.serialNumber=:serial ")
    void setUpdateState (@Param("state") String state, @Param("serial") String serialNumber);
}
