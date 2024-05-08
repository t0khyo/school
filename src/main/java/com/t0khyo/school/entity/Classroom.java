package com.t0khyo.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"level", "classroomOrder", "section"}))
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Classroom level must not be null.")
//    @Max(12)
//    @Min(1)
    private int level;

//    @NotNull(message = "Classroom order must not be blank.")
    private Character classroomOrder;

//    @NotNull(message = "Classroom Section must not be null.")
    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Section section;

    @OneToMany(mappedBy = "classroom", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"classroom", "enrollmentDate", "birthdate", "graduationYear"})
    private List<Student> students;

    @OneToMany(mappedBy = "classroom")
    @JsonIgnoreProperties("classroom")
    private List<Lesson> lessons;

    public Classroom(int level, Character classroomOrder, Section section) {
        this.level = level;
        this.classroomOrder = classroomOrder;
        this.section = section;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
