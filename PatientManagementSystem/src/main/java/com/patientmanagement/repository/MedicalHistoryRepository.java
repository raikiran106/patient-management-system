package com.patientmanagement.repository;

import com.patientmanagement.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByPatientId(String patientId);
}
