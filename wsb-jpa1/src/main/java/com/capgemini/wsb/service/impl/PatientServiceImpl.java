package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.mapper.VisitMapper;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.repository.PatientRepository;
import com.capgemini.wsb.persistence.repository.VisitRepository;
import com.capgemini.wsb.mapper.PatientMapper;
import com.capgemini.wsb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.capgemini.wsb.persistence.dao.PatientDao;

import com.capgemini.wsb.persistence.entity.VisitEntity;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public PatientTO findById(Long id) {
        return patientRepository.findById(id)
                .map(PatientMapper::mapToTO)
                .orElse(null);
    }

    @Override
    public void deletePatient(Long id) {
        PatientEntity patientEntity = patientRepository.findById(id).orElse(null);
        if (patientEntity != null) {
            visitRepository.deleteAllByPatientId(id);
            patientRepository.deleteById(id);
        }
    }

    @Override
    public List<VisitTO> findAllVisitsByPatientId(Long patientId) {
        return patientDao.findAllVisitsByPatientId(patientId).stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }






}
