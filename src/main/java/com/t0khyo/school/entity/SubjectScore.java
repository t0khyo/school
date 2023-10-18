package com.t0khyo.school.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SubjectScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float score;

    // bi-direction
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // uni-direction relation
    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    // uni-direction relation
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
