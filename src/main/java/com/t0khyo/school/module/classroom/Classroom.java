package com.t0khyo.school.module.classroom;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.t0khyo.school.module.lesson.Lesson;
import com.t0khyo.school.module.student.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Classroom name must not be blank.")
    private String roomName;

    @NotNull(message = "Classroom level must not be null.")
    @Enumerated(value = EnumType.STRING)
    private GradeLevel level;

    @JsonManagedReference
    @OneToMany(mappedBy = "classroom", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Student> students;

    @OneToMany(mappedBy = "classroom")
    private List<Lesson> lessons;

    public Classroom(String roomName) {
        this.roomName = roomName;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
