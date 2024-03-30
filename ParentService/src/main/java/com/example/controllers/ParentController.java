package com.example.controllers;

import com.example.entities.Parent;
import com.example.entities.Student;
import com.example.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2")
public class ParentController {
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/parent")
    public List<Parent> getListParent() {
        // Retrieve a list of parents from the repository
        List<Parent> parentList = parentRepository.findAll();

        // Make a single GET request to the student API to retrieve a list of all students
        Student[] students = restTemplate.getForObject("http://localhost:9001/api/v1/students", Student[].class);

        // Convert the array of students to a map for easier lookup
        Map<Long, Student> studentMap = Arrays.stream(students)
                .collect(Collectors.toMap(Student::getStudentId, Function.identity()));

        // Iterate through each parent to fetch associated student information
        for (Parent parent : parentList) {
            // Get the student ID associated with the parent
            Long studentId = parent.getParentId(); // Assuming getParentId() returns studentId

            // Retrieve the student from the map using the student ID
            Student student = studentMap.get(studentId);

            // Set the student for the parent
            parent.setStudent(student);
        }

        // Return the list of parents with associated student information
        return parentList;
    }


}
