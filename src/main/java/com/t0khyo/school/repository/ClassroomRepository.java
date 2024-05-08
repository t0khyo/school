package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Classroom;
import com.t0khyo.school.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByLevelAndClassroomOrderAndSection(Integer level, Character order, Section section);
    @Query("SELECT c FROM Classroom c LEFT JOIN FETCH c.students")
    List<Classroom> findAllClassroomsWithStudents();
}
