package com.enigmacamp.university_app.controller;

import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeacher() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/teacher/{id}")
    public Teacher getTeacherById(@PathVariable String id) {
        return teacherService.getTeacherById(id);
    }

    @PutMapping("/teacher")
    public Teacher updateTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @PostMapping("/teacher")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @DeleteMapping("/teacher/{id}")
    public void deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
    }
}
