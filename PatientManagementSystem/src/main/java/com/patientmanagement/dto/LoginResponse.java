package com.patientmanagement.dto;
import com.patientmanagement.model.Patient;

public class LoginResponse {
    private boolean success;
    private String message;
    private Patient patient; // if role is PATIENT

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
