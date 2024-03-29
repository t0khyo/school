package com.t0khyo.school.module.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.t0khyo.school.module.classroom.Classroom;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Middle name is required.")
    private String middleName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotNull(message = "Birthdate is required.")
    private LocalDate birthdate;

    @PastOrPresent(message = "Enrollment date must be in the past or present.")
    private LocalDate enrollmentDate;

    @NotNull(message = "Graduation year is required.")
    @Digits(integer = 4, fraction = 0, message = "Graduation year should be a 4-digit year.")
    private Short graduationYear;

    @JsonIgnoreProperties("students")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    public Student(String firstName, String middleName, String lastName, LocalDate birthdate, LocalDate enrollmentDate, short graduationYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.enrollmentDate = enrollmentDate;
        this.graduationYear = graduationYear;
    }
}
