package com.patientmanagement.controller;

import com.patientmanagement.dto.LoginRequest;
import com.patientmanagement.dto.LoginResponse;
import com.patientmanagement.model.Patient;
import com.patientmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();
        if ("DOCTOR".equals(request.getRole())) {
            if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
                response.setSuccess(true);
                response.setMessage("Login successful");
                return response;
            }
        } else if ("PATIENT".equals(request.getRole())) {
            Optional<Patient> patient = patientRepository.findByPatientIdAndPasswordAndRole(
                    request.getUsername(), request.getPassword(), "PATIENT");
            if (patient.isPresent()) {
                response.setSuccess(true);
                response.setMessage("Login successful");
                response.setPatient(patient.get());
                return response;
            }
        }
        response.setSuccess(false);
        response.setMessage("Invalid credentials");
        return response;
    }
}
