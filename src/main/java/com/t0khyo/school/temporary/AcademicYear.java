package com.t0khyo.school.temporary;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "academic_year")
public class AcademicYear {
    @OneToMany(mappedBy = "academicYear")
    List<Term> terms;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Start date is required")
    private LocalDate start_date;
    @NotNull(message = "End date is required")
    private LocalDate end_date;

    public AcademicYear(LocalDate start_date, LocalDate end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
    }
}

