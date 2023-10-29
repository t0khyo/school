package com.t0khyo.school.module.classroom;

import com.t0khyo.school.module.student.Student;
import com.t0khyo.school.module.student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    public Classroom createClassroom(Classroom classroom) {
        classroom.setId(0L);
        return classroomRepository.save(classroom);
    }

    public Classroom getClassroomById(long classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with id: " + classroomId + " not found."));
    }

    public Page<Classroom> getAllClassroomsWithPaginationAndSort(int pageNumber, int pageSize, String sortDirection) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "roomName");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
            return classroomRepository.findAll(pageRequest);
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    @Transactional
    public Classroom updateClassroom(Classroom classroom) {
        Classroom existingClassroom = classroomRepository.findById(classroom.getId())
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroom.getId() + " not found"));

        if (classroom.getRoomName() != null && !classroom.getRoomName().isBlank()) {
            existingClassroom.setRoomName(classroom.getRoomName());
        }

        if (classroom.getGradeLevel() != null) {
            existingClassroom.setGradeLevel(classroom.getGradeLevel());
        }

        return existingClassroom;
    }

    public void deleteClassroom(long classroomId) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found."));
        classroomRepository.delete(classroom);
    }

    public List<Classroom> searchByClassroomName(String nameKeyword) {
        return classroomRepository.findByRoomNameContaining(nameKeyword);
    }

    public List<Student> addStudentsToClassroom(long classroomId, List<Long> studentIds) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found."));

        List<Student> savedStudents = new ArrayList<>();

        for (Long studentId : studentIds) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " does not exist."));
            student.setClassroom(classroom);
            savedStudents.add(student);
        }

        classroom.getStudents().addAll(savedStudents);
        classroomRepository.save(classroom);

        return savedStudents;
    }

    public void removeStudentsFromClassroom(long classroomId, List<Long> studentIds) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found."));

        for (Long studentId : studentIds) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " does not exist."));

            if (classroom.getStudents().contains(student)) {
                student.setClassroom(null);
                classroom.getStudents().remove(student);
            }
        }

        classroomRepository.save(classroom);
    }

}
