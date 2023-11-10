package com.t0khyo.school.controller;

import com.t0khyo.school.entity.Subject;
import com.t0khyo.school.entity.Teacher;
import com.t0khyo.school.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Void> createSubject(@Valid @RequestBody Subject subject) {
        subjectService.createSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Page<Subject>> getSubjectsWithPagination(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Subject> subjects = subjectService.getAllSubjectsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(subjects);
    }

    @PutMapping(value = {"/", ""})
    public ResponseEntity<Void> updateSubject(@Valid @RequestBody Subject subject) {
        subjectService.updateSubject(subject);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Subject>> searchSubjectsBySubjectName(@RequestParam String subjectName,
                                                                     @RequestParam(defaultValue = "0") int pageNumber,
                                                                     @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(subjectService.searchSubjectsBySubjectName(subjectName, pageNumber, pageSize));
    }

    @GetMapping("/{subjectId}/teachers")
    public ResponseEntity<List<Teacher>> getSubjectTeachers(@PathVariable Long subjectId) {
        List<Teacher> teachers = subjectService.getSubjectTeachers(subjectId);
        return ResponseEntity.ok(teachers);
    }
}
