package com.capgemini.wsb.persistance.dao;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDaoTest {

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testFindByMinNumberOfVisits() {
        long minNumOfVisits = 2;

        List<DoctorEntity> docs = doctorDao.findByMinNumberOfVisits(minNumOfVisits);

        assertThat(docs).isNotEmpty();
        docs.forEach(doc -> assertThat(doc.getVisits()).hasSizeGreaterThanOrEqualTo((int) minNumOfVisits));

    }


    @Transactional
    @Test
    public void testFindByVisitDateRange() {
        LocalDateTime dateFrom = LocalDateTime.of(2024, 5, 1, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2024, 5, 31, 23, 59);

        List<DoctorEntity> doctors = doctorDao.findByVisitDateRange(dateFrom, dateTo);

        assertThat(doctors).isNotEmpty();

    }

    @Transactional
    @Test
    public void testFindByPatient() {
        String patientLastName = "Smith";

        List<DoctorEntity> doctors = doctorDao.findByPatient(patientLastName);

        assertThat(doctors).isNotEmpty();
        doctors.forEach(doctor -> assertThat(doctor.getVisits()).anyMatch(visit ->
                visit.getPatient().getLastName().equals(patientLastName)));
    }



    @Transactional
    @Test
    public void testCountVisitsOfDoctorInTimeRange() {
        String doctorLastName = "Doe";
        LocalDateTime dateFrom = LocalDateTime.of(2021, 5, 1, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2026, 5, 31, 23, 59);

        // when
        long visitCount = doctorDao.countVisitsOfDoctorInTimeRange(doctorLastName, dateFrom, dateTo);

        // then
        assertThat(visitCount).isGreaterThan(0);
    }

}
