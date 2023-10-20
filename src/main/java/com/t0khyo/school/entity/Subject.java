package com.t0khyo.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subject name is required")
    @Size(max = 100, message = "Subject name cannot exceed 100 characters")
    private String subjectName;

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name cannot exceed 100 characters")
    private String departmentName;

    @Min(value = 0, message = "Maximum score must be a non-negative number")
    private int maxScore;

    @Min(value = 0, message = "Minimum score must be a non-negative number")
    private int minScore;

    @OneToMany(mappedBy = "subject")
    private List<Teacher> teachers;

    public Subject(String subjectName, String departmentName, int maxScore, int minScore) {
        this.subjectName = subjectName;
        this.departmentName = departmentName;
        this.maxScore = maxScore;
        this.minScore = minScore;
    }
}
