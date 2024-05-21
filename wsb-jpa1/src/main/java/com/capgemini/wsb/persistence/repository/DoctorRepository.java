package com.capgemini.wsb.persistence.repository;

import com.capgemini.wsb.persistence.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByDoctorNumber(String doctorNumber);

    List<DoctorEntity> findByLastName(String lastName);

    @Query("SELECT d FROM DoctorEntity d LEFT JOIN FETCH d.visits WHERE d.id = :id")
    Optional<DoctorEntity> findByIdWithVisits(Long id);
}
