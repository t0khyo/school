package com.t0khyo.school.config;

import com.t0khyo.school.entity.*;
import com.t0khyo.school.repository.RoleRepository;
import com.t0khyo.school.service.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SampleDataLoader {
    private final ClassroomService classroomService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final LessonService lessonService;
    private final RoleRepository roleRepository;

    private List<Classroom> classrooms;
    private List<Subject> subjects;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Lesson> lessons;
    private Set<Role> roles;


    @PostConstruct
    public void loadSampleData() {
        loadRoles();
        loadStudents();
        loadClassrooms();
        connectStudentWithClassrooms();

        loadSubjects();
        loadTeachers();
        connectTeachersWithSubjects();

        loadLessons();
        connectLessonsRandomly();
    }

    public void loadClassrooms() {
        classrooms = List.of(
                new Classroom(10, 'A', Section.GENERAL),
                new Classroom(10, 'B', Section.GENERAL),
                new Classroom(10, 'C', Section.GENERAL),
                new Classroom(11, 'A', Section.LITERARY),
                new Classroom(11, 'B', Section.LITERARY),
                new Classroom(11, 'A', Section.SCIENCE),
                new Classroom(12, 'A', Section.SCIENCE),
                new Classroom(12, 'B', Section.LITERARY)
        );
        for (Classroom classroom : classrooms) {
            classroomService.createClassroom(classroom);
        }
    }

    public void loadSubjects() {
        subjects = List.of(
                new Subject("Arabic", "Language"),
                new Subject("English", "Language"),
                new Subject("Spanish", "Language"),
                new Subject("Math", "Mathematics"),
                new Subject("Statistics", "Mathematics"),
                new Subject("Physics", "Science"),
                new Subject("Biology", "Science"),
                new Subject("Chemistry", "Science")
        );
        for (Subject subject : subjects) {
            subjectService.createSubject(subject);
        }
    }

    public void loadStudents() {
        students = List.of(
                new Student("John", "Doe", "Smith", LocalDate.of(2000, 5, 15), LocalDate.of(2010, 9, 1), 2022),
                new Student("Alice", "Johnson", "Brown", LocalDate.of(2001, 8, 12), LocalDate.of(2011, 7, 20),  2023),
                new Student("Robert", "Williams", "Davis", LocalDate.of(1999, 4, 7), LocalDate.of(2009, 11, 14),  2022),
                new Student("Eva", "Martinez", "Wilson", LocalDate.of(2002, 2, 3), LocalDate.of(2012, 5, 18), 2024),
                new Student("Michael", "Harris", "Taylor", LocalDate.of(1998, 10, 25), LocalDate.of(2008, 12, 10),  2021),
                new Student("Sarah", "Lee", "Anderson", LocalDate.of(2003, 7, 9), LocalDate.of(2013, 8, 2), 2025),
                new Student("Daniel", "Clark", "White", LocalDate.of(1997, 1, 12), LocalDate.of(2007, 3, 22),  2021),
                new Student("Emily", "Adams", "Moore", LocalDate.of(2000, 6, 20), LocalDate.of(2010, 9, 1),  2022),
                new Student("James", "Turner", "Collins", LocalDate.of(2001, 9, 15), LocalDate.of(2011, 7, 20), 2023),
                new Student("Olivia", "Carter", "Parker", LocalDate.of(1999, 3, 18), LocalDate.of(2009, 11, 14),  2022),
                new Student("Benjamin", "Murphy", "Lewis", LocalDate.of(2002, 4, 7), LocalDate.of(2012, 5, 18),  2024),
                new Student("Ava", "Hall", "Cooper", LocalDate.of(1998, 8, 3), LocalDate.of(2008, 12, 10),  2021)
        );
        for (Student student : students) {
            studentService.createStudent(student);
        }
    }

    public void loadTeachers() {
        teachers = List.of(
                new Teacher("John", "Smith", "Male", "john.smith@example.com", "+123456789"),
                new Teacher("Alice", "Johnson", "Female", "alice.johnson@example.com", "987654321"),
                new Teacher("David", "Brown", "Male", "david.brown@example.com", "+1122334455"),
                new Teacher("Olivia", "Davis", "Female", "olivia.davis@example.com", "5544332211"),
                new Teacher("James", "Miller", "Male", "james.miller@example.com", "+999888777"),
                new Teacher("Sophia", "Wilson", "Female", "sophia.wilson@example.com", "1112223333"),
                new Teacher("Michael", "Johnson", "Male", "michael.johnson@example.com", "+111222333"),
                new Teacher("Emma", "Davis", "Female", "emma.davis@example.com", "7778889999"),
                new Teacher("William", "Smith", "Male", "william.smith@example.com", "+123443211"),
                new Teacher("Charlotte", "Anderson", "Female", "charlotte.anderson@example.com", "123443211")
        );
        for (Teacher teacher : teachers) {
            teacherService.createTeacher(teacher);
        }
    }

    public void loadLessons(){
        lessons = List.of(
                new Lesson(DayOfWeek.MONDAY, 1),
                new Lesson(DayOfWeek.MONDAY, 2),
                new Lesson(DayOfWeek.MONDAY, 3),
                new Lesson(DayOfWeek.MONDAY, 4),
                new Lesson(DayOfWeek.MONDAY, 5),
                new Lesson(DayOfWeek.MONDAY, 6),
                new Lesson(DayOfWeek.MONDAY, 7)

                ,new Lesson(DayOfWeek.SUNDAY, 1),
                new Lesson(DayOfWeek.SUNDAY, 2),
                new Lesson(DayOfWeek.SUNDAY, 3),
                new Lesson(DayOfWeek.SUNDAY, 4),
                new Lesson(DayOfWeek.SUNDAY, 5),
                new Lesson(DayOfWeek.SUNDAY, 6),
                new Lesson(DayOfWeek.SUNDAY, 7),

                new Lesson(DayOfWeek.TUESDAY, 1),
                new Lesson(DayOfWeek.TUESDAY, 2),
                new Lesson(DayOfWeek.TUESDAY, 3),
                new Lesson(DayOfWeek.TUESDAY, 4),
                new Lesson(DayOfWeek.TUESDAY, 5),
                new Lesson(DayOfWeek.TUESDAY, 6),
                new Lesson(DayOfWeek.TUESDAY, 7),

                new Lesson(DayOfWeek.WEDNESDAY, 1),
                new Lesson(DayOfWeek.WEDNESDAY, 2),
                new Lesson(DayOfWeek.WEDNESDAY, 3),
                new Lesson(DayOfWeek.WEDNESDAY, 4),
                new Lesson(DayOfWeek.WEDNESDAY, 5),
                new Lesson(DayOfWeek.WEDNESDAY, 6),
                new Lesson(DayOfWeek.WEDNESDAY, 7)
        );
        for(Lesson lesson : lessons){
            lessonService.createLesson(lesson);
        }
    }

    public void loadRoles(){
        roles = Set.of(
                new Role("ADMIN"),
                new Role("USER"),
                new Role("TEACHER"),
                new Role("STUDENT")
        );
        for(Role role : roles){
            roleRepository.save(role);
        }
    }

    public void connectLessonsRandomly() {
        Random random = new Random();
        List<Classroom> allClassrooms = classroomService.getAllClassroomsWithPaginationAndSort(0, classrooms.size(), "ASC").getContent();
        List<Subject> allSubjects = subjectService.getAllSubjectsWithPagination(0, subjects.size()).getContent();
        List<Teacher> allTeachers = teacherService.getAllTeachersWithPagination(0, teachers.size(), "ASC").getContent();
        List<Lesson> allLessons = lessonService.getAllLessonsWithPaginationAndSort(0, lessons.size(),"ASC").getContent();

        for (Lesson lesson : allLessons) {
            Classroom randomClassroom = allClassrooms.get(random.nextInt(allClassrooms.size()));
            Subject randomSubject = allSubjects.get(random.nextInt(allSubjects.size()));
            Teacher randomTeacher = allTeachers.get(random.nextInt(allTeachers.size()));

            lesson.setClassroom(randomClassroom);
            lesson.setSubject(randomSubject);
            lesson.setTeacher(randomTeacher);

            lessonService.updateLesson(lesson);
        }

    }

    public void connectStudentWithClassrooms() {
        Random random = new Random();
        List<Classroom> allClassrooms = classroomService.getAllClassroomsWithPaginationAndSort(0, classrooms.size(), "ASC").getContent();
        List<Student> allStudent = studentService.getAllStudentsWithPaginationAndSort(0, students.size(), "ASC").getContent();

        for (Student student : allStudent) {
            Classroom randomClassroom = allClassrooms.get(random.nextInt(classrooms.size()));
            student.setClassroom(randomClassroom);
            studentService.updateStudent(student);
        }
    }

    public void connectTeachersWithSubjects() {
        List<Subject> allSubjects = subjectService.getAllSubjectsWithPagination(0, subjects.size()).getContent();
        List<Teacher> allTeachers = teacherService.getAllTeachersWithPagination(0, teachers.size(), "ASC").getContent();
        Random random = new Random();

        for (Teacher teacher : allTeachers) {
            Subject randomSubject = allSubjects.get(random.nextInt(allSubjects.size()));
            teacher.setSubject(randomSubject);
            teacherService.updateTeacher(teacher);
        }
    }

}
