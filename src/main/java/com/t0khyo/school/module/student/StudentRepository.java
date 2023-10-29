package com.t0khyo.school.module.student;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE CONCAT(s.firstName, ' ', s.middleName, ' ', s.lastName) LIKE %:name%")
    List<Student> searchStudentsByName(@Param("name") String name, Pageable pageable);
}
