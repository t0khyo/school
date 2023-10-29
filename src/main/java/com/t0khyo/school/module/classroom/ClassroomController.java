package com.t0khyo.school.module.classroom;

import com.t0khyo.school.module.student.Student;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Classroom> createdClassrooms = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            createdClassrooms.add(classroomService.createClassroom(classroom));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Classroom>> getAllClassroomsWithPaginationAndSort(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                                                 @RequestParam(defaultValue = "ASC") String sortDirection) {
        List<Classroom> classrooms = classroomService.getAllClassroomsWithPaginationAndSort(pageNumber, pageSize, sortDirection).getContent();
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

    @GetMapping("/findByLevelAndOrderAndSection")
    public ResponseEntity<Classroom> searchClassroom( @RequestBody Classroom classroom) {
        Classroom result = classroomService.findByLevelAndOrderAndSection(classroom);
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
