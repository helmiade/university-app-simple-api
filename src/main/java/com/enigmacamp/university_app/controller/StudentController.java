package com.enigmacamp.university_app.controller;

import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StudentController {
    private final StudentService studentService;

    @RequestMapping("/students")
    public List<Student> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/student")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@Valid @PathVariable String id) {
        studentService.deleteStudent(id);
    }

}
