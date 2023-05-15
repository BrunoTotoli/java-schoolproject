package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.entities.CourseRegistration;
import com.bruno.schoolproject.entities.CourseRegistrationID;
import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.CourseRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private CourseRegistrationRepository courseRegistrationRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course saveStudentOnExistingCourse(Long studentId, Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course dont exists"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student dont exists"));

        CourseRegistrationID courseRegistrationID = new CourseRegistrationID(studentId, courseId);

        CourseRegistration courseRegistration = new CourseRegistration(courseRegistrationID, student, course, LocalDateTime.now());

        courseRegistrationRepository.save(courseRegistration);

        Set<CourseRegistration> ratingsCourse = courseRegistration.getCourse().getStudents();

        ratingsCourse.add(courseRegistration);

        course.setStudents(ratingsCourse);

        return courseRepository.save(course);
    }


}
