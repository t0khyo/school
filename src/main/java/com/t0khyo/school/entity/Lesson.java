package com.t0khyo.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @JsonIgnoreProperties(value = {"lessons", "students"})
    private Classroom classroom;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Min(value = 1, message = "Period order must be a positive integer.")
    @Max(value = 7, message = "Period order must be between 1 and 7.")
    private Integer periodOrder;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnoreProperties(value = {"gender", "email", "phone", "subject"})
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Lesson(DayOfWeek dayOfWeek, Integer periodOrder) {
        this.dayOfWeek = dayOfWeek;
        this.periodOrder = periodOrder;
    }
}
