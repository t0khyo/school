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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String last_name;
    private String gender;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons;

    public Teacher(String firstName, String last_name, String gender, String email, String phone) {
        this.firstName = firstName;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }
}
