package com.patientmanagement.controller;

import com.patientmanagement.model.MedicalHistory;
import com.patientmanagement.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryRepository historyRepository;

    @GetMapping("/{patientId}")
    public List<MedicalHistory> getHistory(@PathVariable String patientId) {
        return historyRepository.findByPatientId(patientId);
    }

    @PostMapping("/add")
    public MedicalHistory addHistory(@RequestBody MedicalHistory history) {
        return historyRepository.save(history);
    }
}
