package com.t0khyo.school.service;

import com.t0khyo.school.entity.Attendance;
import com.t0khyo.school.entity.Classroom;
import com.t0khyo.school.entity.Student;
import com.t0khyo.school.repository.AttendanceRepository;
import com.t0khyo.school.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final ClassroomService classroomService;
    private final ClassroomRepository classroomRepository;
    private final StudentService studentService;

    // <minute> <hour> <day-of-month> <month> <day-of-week> <command>
    @Scheduled(cron = "0 0 6 * 9-12,1-6 SUN-THU", zone = "Africa/Cairo")
    public void dailyAttendanceInitiator() {
        List<Classroom> allClassrooms = classroomRepository.findAllClassroomsWithStudents();

        for (Classroom classroom : allClassrooms) {
            for (Student student : classroom.getStudents()) {
                Attendance attendance = new Attendance();
                attendance.setStatus(false);
                attendance.setStudent(student);
                attendance.setAttendantName("System");
                attendanceRepository.save(attendance);
            }
        }
    }

    public void updateAttendanceStatus(long studentId, boolean status) {
        Student student = studentService.getStudentById(studentId);

        Attendance attendance = attendanceRepository.findByStudentAndDate(student, LocalDate.now())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Attendance record not found for the student on the current date."
                ));

        attendance.setStatus(status);
        attendanceRepository.save(attendance);
    }
}
