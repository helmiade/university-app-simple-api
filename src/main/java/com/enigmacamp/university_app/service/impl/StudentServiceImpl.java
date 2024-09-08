package com.enigmacamp.university_app.service.impl;

import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.repository.StudentRepository;
import com.enigmacamp.university_app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public Student createStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains("Key (email)")){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
            } else if (e.getMessage().contains("Key (phone_number)")) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
            } else if (e.getMessage().contains("Key (nip)")) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "NIM already exists");
            }
            throw e;
        }
    }

    @Override
    public Student updateStudent(Student student) {
        findByIdOrThrowNotFound(student.getId());
        try {
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains("Key (email)")){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
            } else if (e.getMessage().contains("Key (phone_number)")) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
            } else if (e.getMessage().contains("Key (nim)")) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "NIM already exists");
            }
            throw e;
        }
    }

    @Override
    public Student getStudentById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(String id) {
        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return;
        } findByIdOrThrowNotFound(id);

    }

    private Student findByIdOrThrowNotFound(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher with id " + id + " not found"));
    }
}
