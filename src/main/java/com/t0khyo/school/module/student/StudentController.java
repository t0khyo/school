package com.t0khyo.school.module.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@RestController
public class StudentController {
    private final StudentService studentService;

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Void> createStudent(@Valid @RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/create-multiple")
    public ResponseEntity<Void> createMultipleStudents(@RequestBody List<Student> students) {
        for (Student student : students) {
            studentService.createStudent(student);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Page<Student>> getAllStudentsWithPaginationAndSort(@RequestParam(defaultValue = "0") int pageNumber,
                                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                                             @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(studentService.getAllStudentsWithPaginationAndSort(pageNumber, pageSize, sortDirection));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PutMapping(value = {"/", ""})
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("studentId")
    public ResponseEntity<Void> deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByLevelAndOrderAndSection")
    public ResponseEntity<Page<Student>> searchStudentByName(@RequestParam String nameKeyword,
                                                             @RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(studentService.searchStudentsByName(nameKeyword, pageNumber, pageSize));
    }
}
