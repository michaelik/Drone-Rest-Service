package com.droneservice.repository;

import com.droneservice.model.MedicationDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationDeliveryRepository extends JpaRepository<MedicationDelivery, Integer> {
}
