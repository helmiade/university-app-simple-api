package com.enigmacamp.university_app.service;

import com.enigmacamp.university_app.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Student student);
    Student getStudentById(String id);
    List<Student> getAllStudents();
    void deleteStudent(String id);
}
