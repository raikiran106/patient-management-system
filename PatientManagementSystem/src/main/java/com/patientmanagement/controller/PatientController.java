package com.patientmanagement.controller;

import com.patientmanagement.model.Patient;
import com.patientmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable String patientId) {
        return patientRepository.findByPatientId(patientId).orElse(null);
    }

    @PostMapping("/register")
    public Patient registerPatient(@RequestBody Patient patient) {
        patient.setRole("PATIENT");
        return patientRepository.save(patient);
    }
}
