package com.patientmanagement.repository;

import com.patientmanagement.model.TestReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestReportRepository extends JpaRepository<TestReport, Long> {
    List<TestReport> findByPatientId(String patientId);
}
