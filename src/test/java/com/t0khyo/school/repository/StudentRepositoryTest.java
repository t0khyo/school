package com.t0khyo.school.repository;

import com.t0khyo.school.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@DataJpaTest
class StudentRepositoryTest {
    private final StudentRepository studentRepository;


    @Autowired
    public StudentRepositoryTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    public void StudentRepository_SearchStudentByName_ReturnSavedStudent() {
        // Arrange
        Student student1 = Student.builder()
                .firstName("Pikachu")
                .middleName("Electric")
                .lastName("Pokemon")
                .birthdate(LocalDate.of(2004, Month.MARCH, 7))
                .build();

        Student student2 = Student.builder()
                .firstName("Pikachu")
                .middleName("Water")
                .lastName("Melon")
                .birthdate(LocalDate.of(2004, Month.MARCH, 7))
                .build();

        Student student3 = Student.builder()
                .firstName("John")
                .middleName("Doe")
                .lastName("Smith")
                .birthdate(LocalDate.of(2004, Month.MARCH, 7))
                .build();

        studentRepository.saveAll(List.of(student1, student2, student3));

        // Act
        Page<Student> searchResults = studentRepository.searchStudentsByName("Pikachu", Pageable.ofSize(10));
        boolean expected = searchResults.getContent().contains(student1);

        //Assert
        Assertions.assertTrue(expected);
        Assertions.assertEquals(2, searchResults.getTotalElements());
    }
}