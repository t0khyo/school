package com.t0khyo.school.module.classroom;

import com.t0khyo.school.DTO.StudentIdsDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/classroom")
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

    @GetMapping("/search")
    public ResponseEntity<List<Classroom>> searchClassroomByName(String nameKeyword) {
        return ResponseEntity.ok(classroomService.searchByClassroomName(nameKeyword));
    }

    @PostMapping("/add-students/{classroomId}")
    public ResponseEntity<Void> addStudentsToClassroom(@PathVariable long classroomId, @RequestBody StudentIdsDTO studentIdsDTO) {
        classroomService.addStudentsToClassroom(classroomId,studentIdsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove-students/{classroomId}")
    public ResponseEntity<Void>  removeStudentsToClassroom(@PathVariable long classroomId, @RequestBody StudentIdsDTO studentIdsDTO) {
        classroomService.removeStudentsFromClassroom(classroomId,studentIdsDTO);
        return ResponseEntity.ok().build();
    }
}
