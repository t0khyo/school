package com.t0khyo.school.module.subject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Page<Subject> findBySubjectNameContaining(String subjectName, Pageable pageRequest);
}
