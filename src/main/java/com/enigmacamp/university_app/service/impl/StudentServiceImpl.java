package com.enigmacamp.university_app.service.impl;

import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.repository.StudentRepository;
import com.enigmacamp.university_app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        findByIdOrThrowNotFound(student.getId());
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String id) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return List.of();
    }

    @Override
    public void deleteStudent(String id) {

    }

    private Student findByIdOrThrowNotFound(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student ID not found"));
    }
}
