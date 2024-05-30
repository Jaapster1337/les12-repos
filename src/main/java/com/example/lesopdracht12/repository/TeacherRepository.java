package com.example.lesopdracht12.repository;

import com.example.lesopdracht12.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByLastNameContainingIgnoreCase(String lname);

}
