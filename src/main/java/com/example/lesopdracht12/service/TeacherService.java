package com.example.lesopdracht12.service;

import com.example.lesopdracht12.dto.TeacherDto;
import com.example.lesopdracht12.exception.ResourceNotFoundException;
import com.example.lesopdracht12.model.Teacher;
import com.example.lesopdracht12.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos){
        this.repos = repos;

    }

    public TeacherDto createTeacher(TeacherDto teacherDto){
        if(repos.existsByEmail(teacherDto.email)){
            throw new RuntimeException("email already exists");
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        teacher.setEmail(teacherDto.email);
        teacher.setSalary(teacherDto.salary);
        this.repos.save(teacher);
        teacherDto.id = teacher.getId();
        return teacherDto;
    }

    public List<TeacherDto> getAllTeachers(){
        List teachers = repos.findAll();
        return teachers;
    }

    public TeacherDto getTeacherById(long id){
        Teacher teacher = this.repos.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Teacher not found"));
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.id = teacher.getId();
        teacherDto.firstName = teacher.getFirstName();
        teacherDto.lastName = teacher.getLastName();
        teacherDto.dob = teacher.getDob();
        teacherDto.email = teacher.getEmail();
        teacherDto.salary = teacher.getSalary();
        return teacherDto;
    }
}
