package com.example.controllers;
import com.example.entities.Student;
import com.example.repositories.StudentRepository;
import com.example.sevices.StudentRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentRedisService studentRedisService;

    @GetMapping("/students")
    public List<Student> getListStudent(){
        return  studentRepository.findAll();

    }



    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        studentRedisService.save(student);
        studentRepository.save(student);
        return student;
    }
}
