package com.patientmanagement.controller;

import com.patientmanagement.model.Medicine;
import com.patientmanagement.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*")
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/{patientId}")
    public List<Medicine> getMedicines(@PathVariable String patientId) {
        return medicineRepository.findByPatientId(patientId);
    }

    @PostMapping("/add")
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }
    
    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Long id) {
        medicineRepository.deleteById(id);
    }
}
