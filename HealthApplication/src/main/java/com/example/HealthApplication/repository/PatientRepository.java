package com.example.HealthApplication.repository;

import com.example.HealthApplication.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
