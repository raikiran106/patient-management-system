package com.patientmanagement.controller;

import com.patientmanagement.model.Appointment;
import com.patientmanagement.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/{patientId}")
    public List<Appointment> getAppointments(@PathVariable String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
    
    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        appointment.setStatus("PENDING");
        return appointmentRepository.save(appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }
    
    @PutMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestBody String status) {
        Appointment app = appointmentRepository.findById(id).orElse(null);
        if(app != null) {
            app.setStatus(status.replace("\"", ""));
            return appointmentRepository.save(app);
        }
        return null;
    }
}
