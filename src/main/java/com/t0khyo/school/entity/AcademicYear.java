package com.t0khyo.school.entity;

import jakarta.persistence.*;
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
    private LocalDate start_date;
    private LocalDate end_date;

    public AcademicYear(LocalDate start_date, LocalDate end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
    }
}

