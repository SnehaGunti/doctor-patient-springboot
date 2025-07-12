package com.example.HealthApplication.controller;

import com.example.HealthApplication.dto.DoctorRequestDTO;
import com.example.HealthApplication.dto.DoctorResponseDTO;
import com.example.HealthApplication.dto.PatientRequestDTO;
import com.example.HealthApplication.entity.Doctor;
import com.example.HealthApplication.entity.Patient;
import com.example.HealthApplication.service.DoctorService;
import com.example.HealthApplication.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/doctors")
public class HealthController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorService.getAllDoctors().getBody();
    }

    @GetMapping("/{id}")
    public DoctorResponseDTO getDoctorById(@PathVariable Integer id) {
        return doctorService.getDoctorById(id).getBody();
    }

    @PostMapping
    public DoctorResponseDTO addDoctor(@RequestBody DoctorRequestDTO doctor) {
        return doctorService.addDoctor(doctor).getBody();
    }

    @PutMapping("/{id}")
    public DoctorResponseDTO updateDoctor(@PathVariable Integer id, @RequestBody DoctorRequestDTO updatedDoctor) {
        return doctorService.updateDoctor(id, updatedDoctor).getBody();
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Integer id) {
        return doctorService.deleteDoctor(id).getBody();
    }

    @PostMapping("/{docId}/patients")
    public DoctorResponseDTO addPatientToDoctor(@PathVariable Integer docId, @RequestBody PatientRequestDTO patient) {
        return doctorService.addPatientToDoctor(docId, patient).getBody();
    }
}
