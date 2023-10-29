package com.t0khyo.school.module.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Student> createdStudents = new ArrayList<>();
        for (Student student : students) {
            createdStudents.add(studentService.createStudent(student));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Student>> getAllStudentsWithPaginationAndSort(@RequestParam(defaultValue = "0") int pageNumber,
                                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                                             @RequestParam(defaultValue = "ASC") String sortDirection) {
        List<Student> students = studentService.getAllStudentsWithPaginationAndSort(pageNumber, pageSize, sortDirection).getContent();
        return ResponseEntity.ok(students);
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

    @GetMapping("/findByLevelAndOrderAndSection")
    public ResponseEntity<List<Student>> searchStudentByName(@RequestParam String nameKeyword,
                                                             @RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        List<Student> searchResult = studentService.searchStudentsByName(nameKeyword, pageNumber, pageSize);
        return ResponseEntity.ok(searchResult);
    }
}
