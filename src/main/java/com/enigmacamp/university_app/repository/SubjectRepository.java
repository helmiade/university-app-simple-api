package com.enigmacamp.university_app.repository;

import com.enigmacamp.university_app.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findBySubjectNameIgnoreCase(String subjectName);
}
