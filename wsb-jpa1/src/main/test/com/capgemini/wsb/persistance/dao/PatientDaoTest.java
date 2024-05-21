package com.capgemini.wsb.persistance.dao;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldFindPatientsWithMoreThanXVisits() {
        // given
        int visitCount = 2;

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(visitCount);

        // then
        assertThat(patients).isNotEmpty();
        patients.forEach(patient -> assertThat(patient.getVisits().size()).isGreaterThan(visitCount));
    }

    @Transactional
    @Test
    public void testShouldFindFemalePatients() {
        // when
        List<PatientEntity> femalePatients = patientDao.findFemalePatients();

        // then
        assertThat(femalePatients).isNotEmpty();
        femalePatients.forEach(patient -> assertThat(patient.getIsWoman()).isTrue());
    }

    @Transactional
    @Test
    public void testShouldFindPatientsByLastName() {
        // given
        String lastName = "Smith";

        // when
        List<PatientEntity> patients = patientDao.findByLastName(lastName);

        // then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allMatch(patient -> patient.getLastName().equals(lastName));
    }


}
