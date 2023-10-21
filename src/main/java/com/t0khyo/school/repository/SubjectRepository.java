package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findBySubjectNameContaining(String subjectName);
}
