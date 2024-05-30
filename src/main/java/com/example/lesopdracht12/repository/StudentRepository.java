package com.example.lesopdracht12.repository;

import com.example.lesopdracht12.model.Student;
import com.example.lesopdracht12.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentsByStartDateAfter(LocalDate date);
}
