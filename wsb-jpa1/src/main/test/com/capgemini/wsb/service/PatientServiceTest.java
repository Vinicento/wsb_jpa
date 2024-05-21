package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.repository.DoctorRepository;
import com.capgemini.wsb.persistence.repository.PatientRepository;
import com.capgemini.wsb.persistence.repository.VisitRepository;
import com.capgemini.wsb.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PatientServiceImpl patientService;

    @Test
    void testFindByIdReturnsPatientTO() {
        Long patientId = 1L;
        Optional<PatientEntity> patientOptional = patientRepository.findById(patientId);

        assertTrue(patientOptional.isPresent());

        PatientEntity patient = patientOptional.get();
        patient.getVisits().size();  // Initialize lazy-loaded collections

        PatientTO result = patientService.findById(patientId);

        assertNotNull(result);
        assertEquals(patientId, result.getId());
        assertEquals(patient.getFirstName(), result.getFirstName());
        assertEquals(patient.getLastName(), result.getLastName());
        assertEquals(patient.getTelephoneNumber(), result.getTelephoneNumber());
        assertEquals(patient.getEmail(), result.getEmail());
        assertEquals(patient.getPatientNumber(), result.getPatientNumber());
        assertEquals(patient.getDateOfBirth(), result.getDateOfBirth());
        assertFalse(result.getVisits().isEmpty());
        assertEquals(patient.getIsWoman(), result.getIsWoman());
    }

    @Test
    void testDeletePatientAndCascadeVisits() {
        Long patientId = 1L;

        patientService.deletePatient(patientId);

        assertFalse(patientRepository.findById(patientId).isPresent());
        assertTrue(visitRepository.findAllByPatientId(patientId).isEmpty());

        List<DoctorEntity> doctors = doctorRepository.findAll();
        assertFalse(doctors.isEmpty());
    }

    @Test
    void testFindAllVisitsByPatientId() {
        Long patientId = 1L;
        List<VisitEntity> visits = patientDao.findAllVisitsByPatientId(patientId);

        assertNotNull(visits);
        assertFalse(visits.isEmpty());

        List<VisitTO> result = patientService.findAllVisitsByPatientId(patientId);

        assertNotNull(result);
        assertEquals(visits.size(), result.size());
        for (int i = 0; i < visits.size(); i++) {
            assertEquals(visits.get(i).getId(), result.get(i).getId());
        }
    }
}
