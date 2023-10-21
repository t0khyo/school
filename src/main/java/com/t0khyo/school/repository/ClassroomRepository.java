package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByRoomNameContaining(String nameKeyword);
}
