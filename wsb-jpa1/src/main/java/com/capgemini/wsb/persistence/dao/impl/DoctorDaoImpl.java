package com.capgemini.wsb.persistence.dao.impl;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao
{

    @Override
    public List<DoctorEntity> findByMinNumberOfVisits(long pNumOfVisits) {
        return entityManager.createQuery(
                        "select doc from DoctorEntity doc " +
                                "join doc.visits visit " +
                                "group by doc.id " +
                                "having count(visit.id) >= :param1", DoctorEntity.class)
                .setParameter("param1", pNumOfVisits)
                .getResultList();
    }


    @Override
    public List<DoctorEntity> findByVisitDateRange(LocalDateTime pDateFrom, LocalDateTime pDateTo) {
        return entityManager.createQuery(
                        "select distinct doc from DoctorEntity doc " +
                                "join doc.visits visit " +
                                "where visit.time between :dateFrom AND :dateTo", DoctorEntity.class)
                .setParameter("dateFrom", pDateFrom)
                .setParameter("dateTo", pDateTo)
                .getResultList();
    }

    @Override
    public List<DoctorEntity> findByPatient(String pPatientName) {
        return entityManager.createQuery(
                        "select doc from DoctorEntity doc " +
                                "join fetch doc.visits visit " +
                                "join fetch visit.patient patient " +
                                "where patient.lastName like :param1", DoctorEntity.class)
                .setParameter("param1", pPatientName)
                .getResultList();
    }

    @Override
    public List<DoctorEntity> findByTreatmentType(TreatmentType pTreatmentType) {
        return entityManager.createQuery(
                        "select doc from DoctorEntity doc " +
                                "join doc.visits visit " +
                                "join visit.medicalTreatments treatment " +
                                "where treatment.type = :param1", DoctorEntity.class)
                .setParameter("param1", pTreatmentType)
                .setHint(QueryHints.READ_ONLY, true)
                .getResultList();
    }

    @Override
    public long countVisitsOfDoctorInTimeRange(String pDocName, LocalDateTime pDateFrom, LocalDateTime pDateTo) {
        return (long) entityManager.createQuery(
                        "select count(visit) from DoctorEntity doc " +
                                "join doc.visits visit " +
                                "where visit.time between :dateFrom AND :dateTo " +
                                "and doc.lastName like :name")
                .setParameter("dateFrom", pDateFrom)
                .setParameter("dateTo", pDateTo)
                .setParameter("name", pDocName)
                .getSingleResult();
    }

}
