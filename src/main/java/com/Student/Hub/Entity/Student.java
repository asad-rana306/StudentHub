package com.Student.Hub.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Student {

    @Id
    private ObjectId id;
    private String studentName;
    private String regNumber;
    private String fatherName;
    private String cnic;
    @NonNull
    private LocalDate dateOfBirth;
    @NonNull
    private String gender;
    private String contactNumber;
    @NonNull
    private String email;

    private String department;
    private String program;
    private int semester;
    private String batch;
    private String section;
    private List<String> currentSubjects;
    private int completedCredits;
    private double cgpa;
    private double gpa;

    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String guardianName;
    private String guardianContact;
    private String guardianRelation;
    private byte[] image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NonNull
    private String status; // Active / Graduated / Withdrawn
}
