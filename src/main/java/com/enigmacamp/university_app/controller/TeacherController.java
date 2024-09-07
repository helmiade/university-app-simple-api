package com.enigmacamp.university_app.controller;

import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    //    @GetMapping("/api/v1/students")
    @RequestMapping("/api/v1/teachers")
    public List<Teacher> getAllTeacher() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/api/v1/teacher/{id}")
    public Teacher getTeacherById(@PathVariable String id) {
        return teacherService.getTeacherById(id);
    }

    @PutMapping("/api/v1/teacher")
    public Teacher updateTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @PostMapping("/api/v1/teacher")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @DeleteMapping("/api/v1/teacher/{id}")
    public void deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
    }
}
