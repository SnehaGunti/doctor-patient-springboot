package com.example.HealthApplication.controller;

import com.example.HealthApplication.entity.Doctor;
import com.example.HealthApplication.entity.Patient;
import com.example.HealthApplication.exception.DoctorNotFoundException;
import com.example.HealthApplication.repository.DoctorRepository;
import com.example.HealthApplication.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Slf4j
@RestController
@RequestMapping("/api/doctors")
public class HealthController {

    private static final Logger log = LoggerFactory.getLogger(HealthController.class);
    @Autowired
    private DoctorRepository docRepo;

    //get
    @GetMapping
    public List<Doctor> getAllDoctors() {
        log.info("Fetching all doctors");
        return docRepo.findAll();
    }

    //get by id
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Integer id) {
        log.info("Fetching doctor by ID:" , id);
        return docRepo.findById(id).orElseThrow(()-> new DoctorNotFoundException("Doctor not found by ID:" + id));
    }

    //post-create or add
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        log.info("Creating a new Doctor");

        if(doctor.getPatients() != null) {
            for(Patient p : doctor.getPatients()) {
                p.setDoctor(doctor);
            }
        }

        return docRepo.save(doctor);
    }

    //put-update
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Integer id , @RequestBody Doctor updatedDoctor) {
        log.info("Updating doctor");
        updatedDoctor.setId(id);
        return docRepo.save(updatedDoctor);
    }
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Integer id) {
        log.info("Deleting doctor");
        Doctor doctor = docRepo.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id:" + id));
        docRepo.delete(doctor);
        return "Doctor deleted";
    }

    //to add patients
    @Autowired
    private PatientRepository patientRepo;
    @PostMapping("/{docId}/patients")
    public Doctor addPatientToDoctor(@PathVariable Integer docId , @RequestBody Patient patient) {
        Doctor doctor = docRepo.findById(docId).orElseThrow(()-> new DoctorNotFoundException("Doctor not found"));
        //set doc to pat
        patient.setDoctor(doctor);
        patientRepo.save(patient);
        //add patient to doc list
        doctor.getPatients().add(patient);
        return docRepo.save(doctor);
    }

}
