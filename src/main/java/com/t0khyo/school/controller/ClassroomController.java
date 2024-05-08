package com.t0khyo.school.controller;

import com.t0khyo.school.entity.Classroom;
import com.t0khyo.school.entity.Student;
import com.t0khyo.school.service.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Void> createClassroom(@Valid @RequestBody Classroom classroom) {
        classroomService.createClassroom(classroom);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/create-multiple")
    public ResponseEntity<Void> createMultipleClassrooms(@RequestBody List<Classroom> classrooms) {
        for (Classroom classroom : classrooms) {
            classroomService.createClassroom(classroom);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Page<Classroom>> getAllClassroomsWithPaginationAndSort(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size,
                                                                                 @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<Classroom> classrooms = classroomService.getAllClassroomsWithPaginationAndSort(page, size, sortDirection);
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/{classroomId}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable long classroomId) {
        return ResponseEntity.ok(classroomService.getClassroomById(classroomId));
    }

    @PutMapping(value = {"/", ""})
    public ResponseEntity<Void> updateClassroom(Classroom classroom) {
        classroomService.updateClassroom(classroom);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = {"/{classroomId}"})
    public ResponseEntity<Void> deleteClassroom(long classroomId) {
        classroomService.deleteClassroom(classroomId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByLevelAndOrderAndSection")
    public ResponseEntity<Classroom> searchClassroom(@RequestBody Classroom classroom) {
        Classroom result = classroomService.getByLevelAndOrderAndSection(classroom);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add-students/{classroomId}")
    public ResponseEntity<List<Student>> addStudentsToClassroom(@PathVariable long classroomId, @RequestBody List<Long> studentIds) {
        return ResponseEntity.ok(classroomService.addStudentsToClassroom(classroomId, studentIds));
    }

    @DeleteMapping("/remove-students/{classroomId}")
    public ResponseEntity<Void> removeStudentsFromClassroom(@PathVariable long classroomId, @RequestBody List<Long> studentIds) {
        classroomService.removeStudentsFromClassroom(classroomId, studentIds);
        return ResponseEntity.ok().build();
    }
}
