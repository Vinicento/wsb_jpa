package com.capgemini.wsb.service;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import com.capgemini.wsb.dto.PatientTO;
import java.util.List;

public interface PatientService {
    PatientTO findById(Long id);
    void deletePatient(Long id);
    List<VisitTO> findAllVisitsByPatientId(Long patientId);

}
