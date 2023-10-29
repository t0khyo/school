package com.t0khyo.school.module.classroom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.t0khyo.school.module.lesson.Lesson;
import com.t0khyo.school.module.student.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Classroom level must not be null.")
    @Max(12)
    @Min(1)
    private Byte level;

    @NotNull(message = "Classroom order must not be blank.")
    private Character classroomOrder;

    @NotNull(message = "Classroom level must not be null.")
    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Section section;

    @OneToMany(mappedBy = "classroom", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnoreProperties(value = {"classroom","enrollmentDate","birthdate","graduationYear"})
    private List<Student> students;

    @OneToMany(mappedBy = "classroom")
    private List<Lesson> lessons;

    public Classroom(Character classroomOrder) {
        this.classroomOrder = classroomOrder;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
