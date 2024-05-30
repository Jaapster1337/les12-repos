package com.example.lesopdracht12.controller;

import com.example.lesopdracht12.model.Student;
import com.example.lesopdracht12.repository.StudentRepository;
import com.example.lesopdracht12.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repos;

    public StudentController(StudentRepository repos){
        this.repos = repos;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(repos.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        try{
            this.repos.save(student);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + student.getId()).toUriString());
            return ResponseEntity.created(uri).body(student);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body("Creation failed" +e.getMessage());
        }
    }

    @GetMapping("/getByAfterDate")
    public ResponseEntity<List<Student>> getStudentsByStartDateAfter(@RequestParam LocalDate date){
        return ResponseEntity.ok(repos.findStudentsByStartDateAfter(date));
    }
}
