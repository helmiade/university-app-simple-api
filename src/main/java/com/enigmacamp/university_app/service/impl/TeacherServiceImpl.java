package com.enigmacamp.university_app.service.impl;

import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.repository.TeacherRepository;
import com.enigmacamp.university_app.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        try {
            return teacherRepository.save(teacher);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Override
    public Teacher getTeacherById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        findByIdOrThrowNotFound(teacher.getId());
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        if(teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } findByIdOrThrowNotFound(id);
    }

    private Teacher findByIdOrThrowNotFound(String id) {
        return teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher with id " + id + " not found"));
    }
}
