package com.t0khyo.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
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
    private int graduationYear;

    @JsonIgnoreProperties(value = {"students", "lessons"})
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;


    public Student(String firstName, String middleName, String lastName, LocalDate birthdate, LocalDate enrollmentDate, Short graduationYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.enrollmentDate = enrollmentDate;
        this.graduationYear = graduationYear;
    }
}
