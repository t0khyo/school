package com.t0khyo.school.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

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
