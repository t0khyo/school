package com.t0khyo.school.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"student_id","date"}))
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean status;

    @ManyToOne
    private Student student;

    private String attendantName;

    @CreationTimestamp
    private LocalDateTime date;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}
