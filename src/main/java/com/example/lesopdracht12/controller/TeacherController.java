package com.example.lesopdracht12.controller;

import com.example.lesopdracht12.dto.TeacherDto;
import com.example.lesopdracht12.model.Teacher;
import com.example.lesopdracht12.repository.TeacherRepository;
import com.example.lesopdracht12.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers(){
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Teacher>> getteacherByLastName(String lname){
//        return ResponseEntity.ok(repos.findByLastNameContainingIgnoreCase(lname));
//    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) {
        try {
            if (br.hasFieldErrors()) {
                StringBuilder sb = new StringBuilder();
                for(FieldError fe : br.getFieldErrors()){
                    sb.append(fe.getField());
                    sb.append(" : ");
                    sb.append(fe.getDefaultMessage());
                    sb.append("\n");
            }
                return ResponseEntity.badRequest().body(sb.toString());
        }
            teacherDto = teacherService.createTeacher(teacherDto);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + teacherDto.id).toUriString());
            return ResponseEntity.created(uri).body(teacherDto);
        }
        catch(Exception e ){
            return ResponseEntity.unprocessableEntity().body("Creation failed, " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable long id){
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }
}
