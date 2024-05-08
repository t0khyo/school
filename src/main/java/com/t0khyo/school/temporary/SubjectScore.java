package com.t0khyo.school.temporary;

import com.t0khyo.school.entity.Student;
import com.t0khyo.school.entity.Subject;
import com.t0khyo.school.temporary.Term;
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
