package com.t0khyo.school.module.period;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "period")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Period order is required")
    private short periodOrder;

    @NotNull(message = "Start time is required")
    private Time startTime;

    @NotNull(message = "End time is required")
    private Time endTime;

    public Period(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
