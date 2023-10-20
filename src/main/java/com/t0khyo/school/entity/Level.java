package com.t0khyo.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String levelName;
    @Column(unique = true, nullable = false)
    private int levelOrder;

    public Level(String levelName, int levelOrder) {
        this.levelName = levelName;
        this.levelOrder = levelOrder;
    }
}
