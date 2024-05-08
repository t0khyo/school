package com.t0khyo.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subject name is required")
    @Size(max = 35, message = "Subject name cannot exceed 35 characters")
    private String subjectName;

    @NotBlank(message = "Department name is required")
    @Size(max = 35, message = "Department name cannot exceed 35 characters")
    private String departmentName;

    public Subject(String subjectName, String departmentName) {
        this.subjectName = subjectName;
        this.departmentName = departmentName;
    }
}
