package com.danielpm1982.vaccinesRecord.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.danielpm1982.vaccinesRecord.entity.Patient;

@Repository
public interface PatientDAOInterface extends JpaRepository<Patient, Long> {

}
