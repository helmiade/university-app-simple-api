package com.enigmacamp.university_app.service.impl;
import com.enigmacamp.university_app.entity.Subject;
import com.enigmacamp.university_app.repository.SubjectRepository;
import com.enigmacamp.university_app.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        if (subject.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ID cannot be null");
        }
        if (subject.getSubjectName()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Subject Name cannot be null");
        }
        validationSubjectUnique(subject);
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
        if (subject.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be null");
        }
        findByIdOrThrowNotFound(subject.getId());
        validationSubjectUnique(subject);
        return subjectRepository.save(subject);
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

    private void validationSubjectUnique(Subject subject) {
        Optional<Subject> existingSubjectName = subjectRepository.findBySubjectNameIgnoreCase(subject.getSubjectName());

        if(existingSubjectName.isPresent() &&!existingSubjectName.get().getId().equals(subject.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Subject name already exists");
        }
    }
}
