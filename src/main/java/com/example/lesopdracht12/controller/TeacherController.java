package com.example.lesopdracht12.controller;

import com.example.lesopdracht12.model.Teacher;
import com.example.lesopdracht12.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherRepository repos;

    public TeacherController(TeacherRepository repos){
        this.repos = repos;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Teacher>> getteacherByLastName(String lname){
        return ResponseEntity.ok(repos.findByLastNameContainingIgnoreCase(lname));
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        try {
            this.repos.save(teacher);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + teacher.getId()).toUriString());
            return ResponseEntity.created(null).body(teacher);
        }
        catch(Exception e ){
            return ResponseEntity.unprocessableEntity().body("Creation failed");
        }
    }
}
