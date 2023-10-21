package com.t0khyo.school.service;

import com.t0khyo.school.DTO.StudentIdsDTO;
import com.t0khyo.school.entity.Classroom;
import com.t0khyo.school.entity.Level;
import com.t0khyo.school.entity.Student;
import com.t0khyo.school.repository.ClassroomRepository;
import com.t0khyo.school.repository.LevelRepository;
import com.t0khyo.school.repository.StudentRepository;
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
    private final LevelRepository levelRepository;
    private final StudentRepository studentRepository;

    public void createClassroom(Classroom classroom) {
        classroom.setId(0L);
        if (classroom.getLevel() != null) {
            levelRepository.findById(classroom.getLevel().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Level with id: " + classroom.getLevel().getId() + " not found."));
        }
        classroomRepository.save(classroom);
    }

    public Classroom getClassroomById(long classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with id: " + classroomId + " not found."));
    }

    public Page<Classroom> getAllClassroomsWithPaginationAndSort(int pageSize, int pageNumber, String sortDirection) {
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

        if (classroom.getLevel() != null) {
            Level existingLevel = levelRepository.findById(classroom.getLevel().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Level with ID " + classroom.getLevel().getId() + " not found."));
            existingClassroom.setLevel(existingLevel);
        }

        return classroomRepository.save(existingClassroom);
    }

    public void deleteClassroom(long classroomId) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found."));
        classroomRepository.delete(classroom);
    }

    public List<Classroom> searchByClassroomName(String nameKeyword) {
        return classroomRepository.findByRoomNameContaining(nameKeyword);
    }

    public void addStudentsToClassroom(long classroomId, StudentIdsDTO studentIdsDTO) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found."));

        List<Long> studentIds = studentIdsDTO.studentIds();
        List<Student> savedStudents = new ArrayList<>();

        for (Long studentId : studentIds) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " does not exist."));
            student.setClassroom(classroom);
            savedStudents.add(student);
        }

        classroom.getStudents().addAll(savedStudents);
        classroomRepository.save(classroom);
    }

    public void removeStudentsFromClassroom(long classroomId, StudentIdsDTO studentIdsDTO) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found."));

        List<Long> studentIds = studentIdsDTO.studentIds();

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
