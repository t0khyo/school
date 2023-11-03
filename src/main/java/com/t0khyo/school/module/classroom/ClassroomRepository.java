package com.t0khyo.school.module.classroom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByLevelAndClassroomOrderAndSection(Byte level, Character order, Section section);
}
