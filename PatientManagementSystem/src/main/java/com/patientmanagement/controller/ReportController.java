package com.patientmanagement.controller;

import com.patientmanagement.model.TestReport;
import com.patientmanagement.repository.TestReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private TestReportRepository reportRepository;

    @GetMapping("/{patientId}")
    public List<TestReport> getReports(@PathVariable String patientId) {
        return reportRepository.findByPatientId(patientId);
    }

    @PostMapping("/upload")
    public TestReport uploadReport(@RequestBody TestReport report) {
        report.setReportDate(java.time.LocalDate.now().toString());
        return reportRepository.save(report);
    }
}
