package com.t0khyo.school.module.level;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LevelService {
    private final LevelRepository levelRepository;


    public Level createLevel(Level level) {
        level.setId(0L);
        return levelRepository.save(level);
    }

    public Level getLevelById(Long levelId) {
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new EntityNotFoundException("Level with ID " + levelId + " not found."));
    }

    public Page<Level> getAllLevelsWithPaginationAndSort(int pageNumber, int pageSize, String sortDirection) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "levelOrder");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
            return levelRepository.findAll(pageRequest);
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    @Transactional
    public Level updateLevel(Level level) {
        Level existingLevel = levelRepository.findById(level.getId())
                .orElseThrow(() -> new EntityNotFoundException("Level with ID " + level.getId() + " not found."));

        if (level.getLevelName() != null && !level.getLevelName().isBlank()) {
            existingLevel.setLevelName(level.getLevelName());
        }

        return existingLevel;
    }

    public void deleteLevelById(long levelId) {
        Level level = levelRepository.findById(levelId)
                .orElseThrow(() -> new EntityNotFoundException("Level with ID " + levelId + " not found."));

        levelRepository.delete(level);
    }
}
