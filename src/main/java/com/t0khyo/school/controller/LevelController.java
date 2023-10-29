package com.t0khyo.school.controller;

import com.t0khyo.school.entity.Level;
import com.t0khyo.school.service.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {
    private final LevelService levelService;

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Void> createLevel(@Valid @RequestBody Level level) {
        levelService.createLevel(level);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


        @PostMapping("/create-multiple")
        public ResponseEntity<Void> createMultipleLevels(@RequestBody List<Level> levels) {
            List<Level> createdLevels = new ArrayList<>();
            for (Level level : levels) {
                createdLevels.add(levelService.createLevel(level));
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }


    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Level>> getAllLevelsWithPaginationAndSort(@RequestParam(defaultValue = "0") int pageNumber,
                                                                         @RequestParam(defaultValue = "10") int pageSize,
                                                                         @RequestParam(defaultValue = "ASC") String sortDirection) {
        List<Level> levels = levelService.getAllLevelsWithPaginationAndSort(pageNumber, pageSize, sortDirection).getContent();
        return ResponseEntity.ok(levels);
    }

    @GetMapping("/{levelId}")
    public ResponseEntity<Level> getLevelById(@PathVariable long levelId) {
        return ResponseEntity.ok(levelService.getLevelById(levelId));
    }

    @PutMapping(value = {"/", ""})
    public ResponseEntity<Void> updateLevel(@Valid @RequestBody Level level) {
        levelService.updateLevel(level);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<Void> deleteLevel(@PathVariable long levelId) {
        levelService.deleteLevelById(levelId);
        return ResponseEntity.noContent().build();
    }
}
