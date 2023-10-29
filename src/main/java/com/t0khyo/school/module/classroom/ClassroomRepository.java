package com.t0khyo.school.module.classroom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findByLevelAndClassroomOrderAndSection(Byte level, Character order, Section section);
}
