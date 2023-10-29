package com.t0khyo.school.module.lesson;

import com.t0khyo.school.module.classroom.Classroom;
import com.t0khyo.school.module.classroom.ClassroomRepository;
import com.t0khyo.school.module.period.Period;
import com.t0khyo.school.module.period.PeriodRepository;
import com.t0khyo.school.module.subject.Subject;
import com.t0khyo.school.module.subject.SubjectRepository;
import com.t0khyo.school.module.teacher.Teacher;
import com.t0khyo.school.module.teacher.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final PeriodRepository periodRepository;

    public Lesson createLesson(Lesson lesson) {
        lesson.setId(0L);

        if (lesson.getClassroom() != null) {
            Classroom classroom = classroomRepository.findById(lesson.getClassroom().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + lesson.getClassroom().getId() + " not found"));
            lesson.setClassroom(classroom);
        }
        if (lesson.getPeriod() != null) {
            Period period = periodRepository.findById(lesson.getPeriod().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Period with ID " + lesson.getPeriod().getId() + " not found"));
            lesson.setPeriod(period);
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

    public Lesson updateLesson(Lesson lesson) {
        Lesson existingLesson = getLessonById(lesson.getId());

        if (lesson.getClassroom() != null) {
            Classroom classroom = classroomRepository.findById(lesson.getClassroom().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + lesson.getClassroom().getId() + " not found"));
            existingLesson.setClassroom(classroom);
        }
        if (lesson.getPeriod() != null) {
            Period period = periodRepository.findById(lesson.getPeriod().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Period with ID " + lesson.getPeriod().getId() + " not found"));
            existingLesson.setPeriod(period);
        }
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

        return lessonRepository.save(existingLesson);
    }

    public void deleteLesson(Long lessonId) {
        Lesson lesson = getLessonById(lessonId);
        lessonRepository.delete(lesson);
    }
}
