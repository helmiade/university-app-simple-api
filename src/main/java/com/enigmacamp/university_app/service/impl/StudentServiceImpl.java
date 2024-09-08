package com.enigmacamp.university_app.service.impl;
import com.enigmacamp.university_app.entity.Student;
import com.enigmacamp.university_app.repository.StudentRepository;
import com.enigmacamp.university_app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public Student createStudent(Student student) {
        if(student.getNim()== null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NIM cannot be null");
        if(student.getFirstName()== null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name cannot be null");
        validationStudentUnique(student);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        if (student.getId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be null");
        if(student.getNim()== null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NIM cannot be null");
        if(student.getFirstName()== null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name cannot be null");
        findByIdOrThrowNotFound(student.getId());
        validationStudentUnique(student);
        return studentRepository.save(student);
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
        return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id " + id + " not found"));
    }

    private void validationStudentUnique(Student student) {
        Optional<Student> existingStudentEmail = studentRepository.findByEmailIgnoreCase(student.getEmail());
        Optional<Student> existingStudentPhoneNumber = studentRepository.findByPhoneNumber(student.getPhoneNumber());
        Optional<Student> existingStudentNim= studentRepository.findByNimIgnoreCase(student.getNim());

        if(existingStudentEmail.isPresent()&&!existingStudentEmail.get().getId().equals(student.getId())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        if(existingStudentPhoneNumber.isPresent()&&!existingStudentPhoneNumber.get().getId().equals(student.getId())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        if (existingStudentNim.isPresent()&&!existingStudentNim.get().getId().equals(student.getId())) throw new ResponseStatusException(HttpStatus.CONFLICT, "NIM already exists");
    }
}
