package com.enigmacamp.university_app.service.impl;
import com.enigmacamp.university_app.entity.Teacher;
import com.enigmacamp.university_app.repository.TeacherRepository;
import com.enigmacamp.university_app.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        if(teacher.getNip()== null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NIP cannot be null");
        }
        if(teacher.getFirstName()== null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name cannot be null");
        }
        validationTeacherUnique(teacher);
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
        if (teacher.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be null");
        }
        if(teacher.getNip()== null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NIP cannot be null");
        }
        if(teacher.getFirstName()== null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name cannot be null");
        }
        findByIdOrThrowNotFound(teacher.getId());
        validationTeacherUnique(teacher);
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        if(teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return;
        } findByIdOrThrowNotFound(id);
    }

    private Teacher findByIdOrThrowNotFound(String id) {
        return teacherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher with id " + id + " not found"));
    }

    private void validationTeacherUnique(Teacher teacher) {
        Optional<Teacher> existingTeacherEmail = teacherRepository.findByEmailIgnoreCase(teacher.getEmail());
        Optional<Teacher> existingTeacherPhoneNumber = teacherRepository.findByPhoneNumber(teacher.getPhoneNumber());
        Optional<Teacher> existingTeacherNip= teacherRepository.findByNip(teacher.getNip());

        if(existingTeacherEmail.isPresent()&&!existingTeacherEmail.get().getId().equals(teacher.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        } else if(existingTeacherPhoneNumber.isPresent()&&!existingTeacherPhoneNumber.get().getId().equals(teacher.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        } else if (existingTeacherNip.isPresent()&&!existingTeacherNip.get().getId().equals(teacher.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "NIP already exists");
        }
    }
}
