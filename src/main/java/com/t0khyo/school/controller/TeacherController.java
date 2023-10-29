package com.t0khyo.school.controller;

import com.t0khyo.school.entity.Teacher;
import com.t0khyo.school.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacher));
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable long teacherId) {
        return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Teacher>> getTeachersWithPaginationAndSort(@RequestParam(defaultValue = "0") int pageNumber,
                                                                          @RequestParam(defaultValue = "10") int pageSize,
                                                                          @RequestParam(defaultValue = "ASC") String sortDirection) {
        List<Teacher> teachers = teacherService.getAllTeachersWithPagenation(pageNumber, pageSize, sortDirection).getContent();
        return ResponseEntity.ok(teachers);
    }

    @PutMapping(value = {"/", ""})
    public ResponseEntity<Teacher> updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return ResponseEntity.noContent().build();
    }
}
