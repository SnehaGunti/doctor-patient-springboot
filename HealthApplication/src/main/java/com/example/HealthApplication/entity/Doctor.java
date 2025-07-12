package com.example.HealthApplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;
@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


   private String specialization;

    private String name;

    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Patient> patients;
}

