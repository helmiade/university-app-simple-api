package com.enigmacamp.university_app.repository;

import com.enigmacamp.university_app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByEmailIgnoreCase(String email);
    Optional<Student> findByPhoneNumber(String phoneNumber);
    Optional<Student> findByNimIgnoreCase(String nim);
}
