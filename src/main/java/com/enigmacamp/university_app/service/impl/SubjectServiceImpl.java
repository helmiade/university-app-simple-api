package com.enigmacamp.university_app.service.impl;

import com.enigmacamp.university_app.entity.Subject;
import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.repository.SubjectRepository;
import com.enigmacamp.university_app.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        findByIdOrThrowNotFound(subject.getId());
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(String id) {
        subjectRepository.deleteById(id);
    }

    private Subject findByIdOrThrowNotFound(String id) {
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject ID not found"));
    }
}
