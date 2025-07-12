package com.example.HealthApplication.service;

import com.example.HealthApplication.entity.Patient;
import com.example.HealthApplication.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public ResponseEntity<Patient> savePatient(Patient patient) {
        return ResponseEntity.ok(patientRepository.save(patient));
    }
}
