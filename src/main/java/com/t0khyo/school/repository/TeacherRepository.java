package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Subject;
import com.t0khyo.school.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE CONCAT(t.firstName, ' ', t.lastName) LIKE %:name%")
    Page<Teacher> searchTeachersByName(@Param("name") String name, Pageable pageable);

    List<Teacher> findBySubject(Subject theSubject);
}
