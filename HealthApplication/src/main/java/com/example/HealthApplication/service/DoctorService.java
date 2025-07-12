package com.example.HealthApplication.service;

import com.example.HealthApplication.dto.DoctorRequestDTO;
import com.example.HealthApplication.dto.DoctorResponseDTO;
import com.example.HealthApplication.dto.PatientRequestDTO;
import com.example.HealthApplication.dto.PatientResponseDTO;
import com.example.HealthApplication.entity.Doctor;
import com.example.HealthApplication.entity.Patient;
import com.example.HealthApplication.exception.DoctorNotFoundException;
import com.example.HealthApplication.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    //convert doctor entity to doctorResponseDTO
    private DoctorResponseDTO convertEntityToDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());

        if(doctor.getPatients() != null) {
            List<PatientResponseDTO> patients = doctor.getPatients()
                    .stream()
                    .map(this::convertEntityToDTO)
                    .collect(Collectors.toList());
            dto.setPatients(patients);
        }
        return dto;
    }

    //convert patient entity to pateintRespinseDTO
    private PatientResponseDTO convertEntityToDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setDisease(patient.getDisease());
        return dto;
    }

    //convert dto to entitty
    private Doctor convertDTOToEntity(DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        if(doctor.getPatients() != null) {
            List<Patient> patients = dto.getPatients().stream()
                    .map(this::convertDTOToEntity)
                    .collect(Collectors.toList());

            for(Patient p : patients) {
                p.setDoctor(doctor);
            }
            doctor.setPatients(patients);
        }
        return doctor;
    }

    //cpnvert pateint dto to patirnt entity
    private Patient convertDTOToEntity(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setDisease(dto.getDisease());
        return patient;
    }







    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        log.info("Fetching all doctors");
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorResponseDTO> dtos = doctors.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<DoctorResponseDTO> getDoctorById(Integer id) {
        log.info("Fetching doctor by ID: {}", id);
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by ID: " + id));
        return ResponseEntity.ok(convertEntityToDTO(doctor));
    }

    public ResponseEntity<DoctorResponseDTO> addDoctor(DoctorRequestDTO dto) {
        log.info("Creating a new doctor");
        Doctor doctor = convertDTOToEntity(dto);
        Doctor saved = doctorRepository.save(doctor);
        return ResponseEntity.ok(convertEntityToDTO(saved));
    }

    public ResponseEntity<DoctorResponseDTO> updateDoctor(Integer id, DoctorRequestDTO dto) {
        log.info("Updating doctor with ID: {}", id);
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with ID: " + id));

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        Doctor updated = doctorRepository.save(doctor);
        return ResponseEntity.ok(convertEntityToDTO(updated));
    }

    public ResponseEntity<String> deleteDoctor(Integer id) {
        log.info("Deleting doctor with ID: {}", id);
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
        return ResponseEntity.ok("Doctor deleted");
    }

    public ResponseEntity<DoctorResponseDTO> addPatientToDoctor(Integer docId, PatientRequestDTO pdto) {
        Doctor doctor = doctorRepository.findById(docId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

        Patient patient = convertDTOToEntity(pdto);
        patient.setDoctor(doctor);
        doctor.getPatients().add(patient);
        Doctor updated = doctorRepository.save(doctor);
        return ResponseEntity.ok(convertEntityToDTO(updated));
    }
}
