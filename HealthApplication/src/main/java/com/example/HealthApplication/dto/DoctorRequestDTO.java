package com.example.HealthApplication.dto;
import lombok.Data;

import java.util.*;
@Data
public class DoctorRequestDTO {
    private String name;
    private String specialization;
    private List<PatientRequestDTO> patients;

}
