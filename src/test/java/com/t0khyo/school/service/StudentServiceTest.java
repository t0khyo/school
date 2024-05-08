package com.t0khyo.school.service;

import com.t0khyo.school.entity.Classroom;
import com.t0khyo.school.entity.Student;
import com.t0khyo.school.repository.ClassroomRepository;
import com.t0khyo.school.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    StudentService underTestService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ClassroomRepository classroomRepository;

    @BeforeEach
    void setUp() {
        underTestService = new StudentService(studentRepository, classroomRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void Can_CreateStudent() {
        // Arrange
        Student student = new Student("John", "Doe", "Smith",
                LocalDate.of(2000, 5, 15),
                LocalDate.of(2010, 9, 1), 2022);

        student.setClassroom(new Classroom());

        // Act
        // Assert
        assertThrows(EntityNotFoundException.class,
                () -> underTestService.createStudent(student));

        verify(studentRepository, never())
                .save(any());
    }

    @Test
    void getStudentById() {
    }

    @Test
    void Can_getAllStudentsWithPaginationAndSort() {
        underTestService.getAllStudentsWithPaginationAndSort(0, 10, "ASC");

        verify(studentRepository).findAll(any(PageRequest.class));
    }

    @Test
    @Disabled
    void searchStudentsByName() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }
}