package com.enigmacamp.university_app.service;

import com.enigmacamp.university_app.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher getTeacherById(String id);
    List<Teacher> getAllTeachers();
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacher(String id);
}
