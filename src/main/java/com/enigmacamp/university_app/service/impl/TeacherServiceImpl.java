package com.enigmacamp.university_app.service.impl;

import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.repository.TeacherRepository;
import com.enigmacamp.university_app.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
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
        teacherRepository.deleteById(id);

    }

    private Teacher findByIdOrThrowNotFound(String id) {
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher ID not found"));
    }
}
