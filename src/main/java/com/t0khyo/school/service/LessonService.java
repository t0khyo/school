package com.t0khyo.school.service;

import com.t0khyo.school.entity.Classroom;
import com.t0khyo.school.entity.Lesson;
import com.t0khyo.school.entity.Subject;
import com.t0khyo.school.entity.Teacher;
import com.t0khyo.school.repository.ClassroomRepository;
import com.t0khyo.school.repository.LessonRepository;
import com.t0khyo.school.repository.SubjectRepository;
import com.t0khyo.school.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;

    public Lesson createLesson(Lesson lesson) {
        lesson.setId(0L);

        if (lesson.getClassroom() != null) {
            Classroom classroom = classroomRepository.findById(lesson.getClassroom().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + lesson.getClassroom().getId() + " not found"));
            lesson.setClassroom(classroom);
        }
        if (lesson.getTeacher() != null) {
            Teacher teacher = teacherRepository.findById(lesson.getTeacher().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher with ID " + lesson.getTeacher().getId() + " not found"));
            lesson.setTeacher(teacher);
        }
        if (lesson.getSubject() != null) {
            Subject subject = subjectRepository.findById(lesson.getSubject().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + lesson.getSubject().getId() + " not found"));
            lesson.setSubject(subject);
        }

        return lessonRepository.save(lesson);
    }

    public Lesson getLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson with ID " + lessonId + " not found"));
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Page<Lesson> getAllLessonsWithPaginationAndSort(int page, int size, String sortDirection) {
        if ("ASC".equalsIgnoreCase(sortDirection) || "DESC".equalsIgnoreCase(sortDirection)) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "id");
            PageRequest pageRequest = PageRequest.of(page, size, sort);
            return lessonRepository.findAll(pageRequest);
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection + ", please use either 'ASC' or 'DESC'.");
        }
    }

    public Lesson updateLesson(Lesson lesson) {
        Lesson existingLesson = getLessonById(lesson.getId());

        if (lesson.getClassroom() != null) {
            Classroom classroom = classroomRepository.findById(lesson.getClassroom().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + lesson.getClassroom().getId() + " not found"));
            existingLesson.setClassroom(classroom);
        }
//        if (lesson.getPeriodOrder() > 0) {
//            existingLesson.setPeriodOrder(lesson.getPeriodOrder());
//        }
        if (lesson.getTeacher() != null) {
            Teacher teacher = teacherRepository.findById(lesson.getTeacher().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher with ID " + lesson.getTeacher().getId() + " not found"));
            existingLesson.setTeacher(teacher);
        }
        if (lesson.getSubject() != null) {
            Subject subject = subjectRepository.findById(lesson.getSubject().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject with ID " + lesson.getSubject().getId() + " not found"));
            existingLesson.setSubject(subject);
        }
        if (lesson.getDayOfWeek() != null) {
            existingLesson.setDayOfWeek(lesson.getDayOfWeek());
        }

        return lessonRepository.save(existingLesson);
    }

    public void deleteLesson(Long lessonId) {
        Lesson lesson = getLessonById(lessonId);
        lessonRepository.delete(lesson);
    }
}
