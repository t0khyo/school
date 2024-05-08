package com.t0khyo.school.temporary;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero(message = "Term number must be a non-negative number")
    private int termNumber;

    @NotNull(message = "End date is required")
    private LocalDate startDate;

    @NotNull(message = "Start date is required")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    public Term(short termNumber, LocalDate startDate, LocalDate endDate) {
        this.termNumber = termNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
