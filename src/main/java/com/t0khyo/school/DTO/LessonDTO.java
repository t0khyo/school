package com.t0khyo.school.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LessonDTO {
    @NotNull
    @Min(1)
    @Max(7)
    private int period;
    @NotNull
    private String DayOfWeek;
    @NotNull
    private long classroomId;
    @NotNull
    private long teacherId;
    @NotNull
    private long subjectId;
}
