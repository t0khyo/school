package com.t0khyo.school.service;

import com.t0khyo.school.entity.Subject;
import com.t0khyo.school.entity.Teacher;
import com.t0khyo.school.repository.SubjectRepository;
import com.t0khyo.school.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    private void createTeacher(Teacher teacher) {
        teacher.setId(0L);
        if (teacher.getSubject() != null) {
            Subject theSubject = subjectRepository.findById(teacher.getSubject().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + teacher.getSubject().getId() + " not found."));
            teacher.setSubject(theSubject);
        }
        teacherRepository.save(teacher);
    }

    public Teacher getTeacherById(long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id: " + teacherId + " not found."));
    }

    public Page<Teacher> getAllTeachersWithPagenation(int pageNumber, int pageSize, String sortDirection) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "firstName", "lastName");
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
            return teacherRepository.findAll(pageRequest);
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    @Transactional
    public void updateTeacher(Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(teacher.getId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher with ID " + teacher.getId() + " not found"));

        if (teacher.getFirstName() != null && !teacher.getFirstName().isBlank()) {
            existingTeacher.setFirstName(teacher.getFirstName());
        }

        if (teacher.getLastName() != null && !teacher.getLastName().isBlank()) {
            existingTeacher.setLastName(teacher.getLastName());
        }

        if (teacher.getGender() != null) {
            existingTeacher.setGender(teacher.getGender());
        }

        if (teacher.getEmail() != null && !teacher.getEmail().isBlank()) {
            existingTeacher.setEmail(teacher.getEmail());
        }

        if (teacher.getPhone() != null && !teacher.getPhone().isBlank()) {
            existingTeacher.setPhone(teacher.getPhone());
        }
        if (teacher.getSubject() != null) {
            Subject newSubject = subjectRepository.findById(teacher.getSubject().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + teacher.getSubject().getId() + " not found."));
            existingTeacher.setSubject(newSubject);
        }
    }

    public void deleteTeacher(long teacherId) {
        teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with ID " + teacherId + " not found."));

        teacherRepository.deleteById(teacherId);
    }

    public List<Teacher> serchTeacherByName(String nameKeyword) {
        return teacherRepository.searchTeachersByName(nameKeyword);
    }
}
