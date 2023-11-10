package com.t0khyo.school.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull
    private String gender;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(\\+\\d+|\\d+)$",
            message = "Phone number must be a valid number or start with a plus sign followed by numbers")
    @Size(min = 8, max = 15, message = "Phone number must be between 5 and 15 characters")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Teacher(String firstName, String lastName, String gender, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }
}
