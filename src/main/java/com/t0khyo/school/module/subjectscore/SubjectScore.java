package com.t0khyo.school.module.subjectscore;

import com.t0khyo.school.module.student.Student;
import com.t0khyo.school.module.subject.Subject;
import com.t0khyo.school.module.term.Term;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(value = 0, message = "Score cannot be less than 0")
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
