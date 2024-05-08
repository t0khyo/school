package com.t0khyo.school.controller;

import com.t0khyo.school.entity.Lesson;
import com.t0khyo.school.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/lessons")
public class LessonController {
    private final LessonService lessonService;

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Void> createLesson(@RequestBody Lesson lesson) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Page<Lesson>> getAllLessonsWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(defaultValue = "ASC") String sort) {
        return ResponseEntity.ok(lessonService.getAllLessonsWithPaginationAndSort(page, size, sort));
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonService.getLessonById(lessonId));
    }
}
