package com.t0khyo.school.entity;

import jakarta.persistence.*;
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

    private String subjectName;
    private String departmentName;
    private int maxScore;
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
