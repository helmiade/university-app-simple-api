package com.enigmacamp.university_app.controller;

import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

//    @GetMapping("/api/v1/students")
    @RequestMapping("/api/v1/students")
    public List<Student> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/api/v1/student/{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/api/v1/student")
    public Student updateStudent(@Valid @RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @PostMapping("/api/v1/student")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/api/v1/student/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

}
