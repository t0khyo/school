package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Attendance;
import com.t0khyo.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByStudentAndDate(Student student, LocalDate now);
}
