package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao extends Dao<PatientEntity, Long> {
    List<PatientEntity> findByLastName(String lastName);
    List<VisitEntity> findAllVisitsByPatientId(Long patientId);
    List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount);
    List<PatientEntity> findFemalePatients();
}
