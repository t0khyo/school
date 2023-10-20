package com.t0khyo.school.service;

import com.t0khyo.school.entity.Student;
import com.t0khyo.school.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository repository;

    public void  createStudent(Student student) {
        student.setId(0L);
        if (student.getEnrollmentDate() == null){
            student.setEnrollmentDate(LocalDate.now());
        }
        repository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return repository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Page<Student> getStudentsWithPagination(int pageNumber, int pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize,Sort.Direction.ASC));
    }

    public Page<Student> getStudentsWithPaginationAndSort(int pageNumber, int pageSize, String sortDirection) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.valueOf(sortDirection)));
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    public Page<Student> getStudentsWithPaginationAndSortByField(int pageNumber, int pageSize, String sortDirection, String field) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.valueOf(sortDirection), field));
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    public List<Student> searchStudentsByKeyword(String nameKeyword, int pageSize, int pageNumber) {
        return repository.searchStudentsByName(nameKeyword, PageRequest.of(pageSize,pageSize, Sort.Direction.ASC));
    }

    @Transactional
    public Student updateStudent(Student student) {
        Student existingStudent = repository.findById(student.getId()).orElseThrow(() -> new EntityNotFoundException("Student with ID " + student.getId() + " not found"));

        if (student.getFirstName() != null && !student.getFirstName().isBlank()) {
            existingStudent.setFirstName(student.getFirstName());
        }

        if (student.getMiddleName() != null && !student.getMiddleName().isBlank()) {
            existingStudent.setMiddleName(student.getMiddleName());
        }

        if (student.getLastName() != null && !student.getLastName().isBlank()) {
            existingStudent.setLastName(student.getLastName());
        }

        if (student.getBirthdate() != null) {
            existingStudent.setBirthdate(student.getBirthdate());
        }

        if (student.getEnrollmentDate() != null) {
            existingStudent.setEnrollmentDate(student.getEnrollmentDate());
        }

        if (student.getGraduationYear() != 0) {
            existingStudent.setGraduationYear(student.getGraduationYear());
        }

        return existingStudent;
    }

    public void deleteStudent(long studentId) {
        Student student = repository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));
        repository.delete(student);
    }
}
