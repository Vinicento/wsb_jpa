package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorDao extends Dao<DoctorEntity, Long> {

    List<DoctorEntity> findByMinNumberOfVisits(long pNumOfVisits);

    List<DoctorEntity> findByVisitDateRange(LocalDateTime pDateFrom, LocalDateTime pDateTo);

    List<DoctorEntity> findByPatient(String pPatientName);

    List<DoctorEntity> findByTreatmentType(TreatmentType pTreatmentType);

    long countVisitsOfDoctorInTimeRange(String pDocName, LocalDateTime pDateFrom, LocalDateTime pDateTo);
}
