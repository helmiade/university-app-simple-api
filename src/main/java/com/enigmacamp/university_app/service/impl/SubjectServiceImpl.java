package com.enigmacamp.university_app.service.impl;

import com.enigmacamp.university_app.entity.Subject;
import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.repository.SubjectRepository;
import com.enigmacamp.university_app.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        try {
            return subjectRepository.save(subject);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Subject already exists");
        }
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
        try {
            return subjectRepository.save(subject);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Subject already exists");
        }
    }

    @Override
    public void deleteSubject(String id) {
        if(subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return;
        } findByIdOrThrowNotFound(id);
    }

    private Subject findByIdOrThrowNotFound(String id) {
        return subjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id " + id + " not found"));
    }
}
