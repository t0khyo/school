package com.t0khyo.school.service;

import com.t0khyo.school.entity.Classroom;
import com.t0khyo.school.entity.Student;
import com.t0khyo.school.repository.ClassroomRepository;
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
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    public Student createStudent(Student student) {
        student.setId(0L);
        if (student.getEnrollmentDate() == null) { // todo
            student.setEnrollmentDate(LocalDate.now());
        }
        if (student.getClassroom() != null) {
            Classroom theClassroom = classroomRepository.findById(student.getClassroom().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Class with ID " + student.getClassroom().getId() + " not found"));
            student.setClassroom(theClassroom);
        }
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));
    }

    public Page<Student> getAllStudentsWithPaginationAndSort(int pageNumber, int pageSize, String sortDirection) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "firstName", "middleName", "lastName");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
            return studentRepository.findAll(pageRequest);
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    public Page<Student> getAllStudentsWithPaginationAndSortByField(int pageNumber, int pageSize, String sortDirection, String field) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            return studentRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.valueOf(sortDirection), field));
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    public List<Student> searchStudentsByName(String nameKeyword, int pageNumber, int pageSize) {
        return studentRepository.searchStudentsByName(nameKeyword, PageRequest.of(pageNumber, pageSize));
    }

    @Transactional
    public Student updateStudent(Student student) {
        Student existingStudent = studentRepository.findById(student.getId()).orElseThrow(() -> new EntityNotFoundException("Student with ID " + student.getId() + " not found"));

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

        if (student.getGraduationYear() != null) {
            existingStudent.setGraduationYear(student.getGraduationYear());
        }

        if (student.getClassroom() != null) {
            Classroom classroom = classroomRepository.findById(student.getClassroom().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Student with ID " + student.getClassroom().getId() + " not found"));
            existingStudent.setClassroom(student.getClassroom());
        }

        return existingStudent;
    }

    public void deleteStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));
        studentRepository.delete(student);
    }
}
