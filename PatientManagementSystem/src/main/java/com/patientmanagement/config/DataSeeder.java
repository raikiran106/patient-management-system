package com.patientmanagement.config;

import com.patientmanagement.model.*;
import com.patientmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicalHistoryRepository historyRepository;
    @Autowired
    private TestReportRepository reportRepository;

    @Override
    public void run(String... args) throws Exception {
        if(patientRepository.count() == 0) {
            Patient p1 = new Patient();
            p1.setPatientId("PT001");
            p1.setName("John Doe");
            p1.setAge(35);
            p1.setGender("Male");
            p1.setPhone("1234567890");
            p1.setPassword("password");
            p1.setRole("PATIENT");
            patientRepository.save(p1);

            Appointment a1 = new Appointment();
            a1.setPatientId("PT001");
            a1.setDoctorName("Dr. Smith");
            a1.setAppointmentDate(LocalDate.now().plusDays(2).toString());
            a1.setAppointmentTime("10:00 AM");
            a1.setStatus("CONFIRMED");
            appointmentRepository.save(a1);

            Medicine m1 = new Medicine();
            m1.setPatientId("PT001");
            m1.setMedicineName("Paracetamol");
            m1.setDosage("500mg");
            m1.setReminderTime("08:00 AM");
            m1.setDuration("5 days");
            medicineRepository.save(m1);
            
            MedicalHistory h1 = new MedicalHistory();
            h1.setPatientId("PT001");
            h1.setDiagnosis("Viral Fever");
            h1.setTreatment("Rest and Paracetamol");
            h1.setDoctorNotes("Patient advised to drink plenty of fluids.");
            h1.setVisitDate(LocalDate.now().minusDays(5).toString());
            historyRepository.save(h1);
            
            TestReport r1 = new TestReport();
            r1.setPatientId("PT001");
            r1.setTestName("Complete Blood Count (CBC)");
            r1.setResult("Normal");
            r1.setReportDate(LocalDate.now().minusDays(4).toString());
            r1.setReportFilePath("/reports/cbc_pt001.pdf");
            reportRepository.save(r1);
        }
    }
}
