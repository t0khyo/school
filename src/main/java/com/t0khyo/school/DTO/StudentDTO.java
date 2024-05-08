package com.t0khyo.school.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Middle name is required.")
    private String middleName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotNull(message = "Birthdate is required.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @PastOrPresent(message = "Enrollment date must be in the past or present.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate enrollmentDate;

    @NotNull(message = "Graduation year is required.")
    @Digits(integer = 4, fraction = 0, message = "Graduation year should be a 4-digit year.")
    private int graduationYear;

    private Long classroomId;
}
