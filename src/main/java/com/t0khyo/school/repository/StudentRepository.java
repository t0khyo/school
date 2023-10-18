package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameContainingOrMiddleNameContainingOrLasNameContaining(String keyword);
}
