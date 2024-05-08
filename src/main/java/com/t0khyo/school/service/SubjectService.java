package com.t0khyo.school.service;

import com.t0khyo.school.entity.Subject;
import com.t0khyo.school.entity.Teacher;
import com.t0khyo.school.repository.SubjectRepository;
import com.t0khyo.school.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public void createSubject(Subject subject) {
        subject.setId(0L);
        subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + subjectId + " not found"));
    }

    public Page<Subject> getAllSubjectsWithPagination(int pageNumber, int pageSize) {
        return subjectRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "subjectName"));
    }

    @Transactional
    public void updateSubject(Subject subject) {
        Subject existingSubject = subjectRepository.findById(subject.getId())
                .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + subject.getId() + " not found"));

        if (subject.getSubjectName() != null && !subject.getSubjectName().isBlank()) {
            existingSubject.setSubjectName(subject.getSubjectName());
        }

        if (subject.getDepartmentName() != null && !subject.getDepartmentName().isBlank()) {
            existingSubject.setDepartmentName(subject.getDepartmentName());
        }
    }

    public void deleteSubject(long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + subjectId + " not found"));
        subjectRepository.delete(subject);
    }

    public Page<Subject> searchSubjectsBySubjectName(String subjectName, int pageNumber, int pageSize) {
        return subjectRepository.findBySubjectNameContaining(subjectName, PageRequest.of(pageNumber, pageSize));
    }

    public List<Teacher> getSubjectTeachers(long subjectId) {
        Subject theSubject = subjectRepository.findById(subjectId).orElseThrow(() -> new EntityNotFoundException("Subject with ID " + subjectId + " not found."));
        return teacherRepository.findBySubject(theSubject);
    }
}
