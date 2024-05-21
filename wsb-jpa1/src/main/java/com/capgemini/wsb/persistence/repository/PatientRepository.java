package com.capgemini.wsb.persistence.repository;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    PatientEntity findByPatientNumber(String patientNumber);

    @Query("SELECT p FROM PatientEntity p LEFT JOIN FETCH p.address LEFT JOIN FETCH p.visits WHERE p.id = :id")
    PatientEntity findByIdWithDetails(Long id);


}
