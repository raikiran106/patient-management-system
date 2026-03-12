package com.patientmanagement.repository;

import com.patientmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPatientId(String patientId);
    Optional<Patient> findByPatientIdAndPasswordAndRole(String patientId, String password, String role);
}
