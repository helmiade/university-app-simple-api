package com.enigmacamp.university_app.service;

import com.enigmacamp.university_app.entity.Subject;

import java.util.List;

public interface SubjectService {
    Subject createSubject(Subject subject);
    List<Subject> getAllSubjects();
    Subject getSubjectById(String id);
    Subject updateSubject(Subject subject);
    void deleteSubject(String id);
}
