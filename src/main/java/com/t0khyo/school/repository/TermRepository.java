package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
