package com.example.HealthApplication.dto;
import lombok.Data;

import java.util.*;

@Data
public class DoctorResponseDTO {
    private Integer id;
    private String name;
    private String specialization;
    private List<PatientResponseDTO> patients;
}
