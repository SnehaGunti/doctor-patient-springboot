
# Doctor-Patient Management Application (Spring Boot)

This is a simple Spring Boot application for managing doctors and their patients.  
It allows adding, updating, retrieving, and deleting doctor and patient records through REST APIs.



## Features

- Add / Update / Delete Doctors
- Add / Update Patients under a Doctor
- Get Doctor Details with Patient List
- DTO (Data Transfer Object) implementation
- Spring Security authentication added (Basic Auth)



## Project Structure


HealthApplication/
├── controller/
│   └── HealthController.java
├── service/
│   ├── DoctorService.java
│   └── PatientService.java
├── dto/
│   ├── DoctorRequestDTO.java
│   ├── DoctorResponseDTO.java
│   ├── PatientRequestDTO.java
│   └── PatientResponseDTO.java
├── entity/
│   ├── Doctor.java
│   └── Patient.java
├── security/
│   └── SecurityConfig.java
├── repository/
│   ├── DoctorRepository.java
│   └── PatientRepository.java




##  Security

- Basic authentication is enabled using Spring Security.
- You must use a username and password to access APIs.



##  Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- Maven
- Postman (for API testing)



## API Endpoints

| Method | Endpoint                       | Description                        |
|--------|--------------------------------|------------------------------------|
| GET    | `/api/doctors`                 | Get all doctors                    |
| GET    | `/api/doctors/{id}`            | Get doctor by ID                   |
| POST   | `/api/doctors`                 | Add a new doctor                   |
| PUT    | `/api/doctors/{id}`            | Update doctor                      |
| DELETE | `/api/doctors/{id}`            | Delete doctor                      |
| POST   | `/api/doctors/{docId}/patients`| Add patient to a doctor            |




##  Testing the API

Use [Postman](https://www.postman.com/) or any REST client.




##  Author

Sneha Gunti
GitHub: [@SnehaGunti](https://github.com/SnehaGunti)



##  Future Improvements

- JWT-based authentication
- Frontend integration (React)
- Role-based access control
- Exception handling improvements
