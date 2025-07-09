package com.example.HealthApplication.exception;

public class DoctorNotFoundException extends RuntimeException{

    public DoctorNotFoundException(String msg) {
        super(msg);
    }
}
