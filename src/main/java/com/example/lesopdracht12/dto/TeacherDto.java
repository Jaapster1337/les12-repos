package com.example.lesopdracht12.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherDto {

    public Long id;
    public String firstName;
    @NotBlank
    @Size(min=2, max=64)
    public String lastName;
    public LocalDate dob;
    @Email(message = "invalide email")
    public String email;
    @Max(100000)
    public int salary;
}
