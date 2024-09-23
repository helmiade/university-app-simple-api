package com.enigmacamp.university_app.controller;
import com.enigmacamp.university_app.entity.Subject;
import com.enigmacamp.university_app.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SubjectController {
    private final SubjectService subjectService;

    @RequestMapping("/subjects")
    public List<Subject> getAllSubject() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/subject/{id}")
    public Subject getsubjectById(@PathVariable String id) {
        return subjectService.getSubjectById(id);
    }

    @PutMapping("/subject")
    public Subject updateSubject(@RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    @PostMapping("/subject")
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @DeleteMapping("/subject/{id}")
    public void deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
    }
}
