package com.enigmacamp.university_app.repository;

import com.enigmacamp.university_app.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Optional<Teacher> findByEmailIgnoreCase(String email);
    Optional<Teacher> findByPhoneNumber(String phoneNumber);
    Optional<Teacher> findByNip(String nip);
}
