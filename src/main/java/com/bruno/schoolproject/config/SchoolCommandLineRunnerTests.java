package com.bruno.schoolproject.config;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.entities.CourseRegistration;
import com.bruno.schoolproject.entities.CourseRegistrationID;
import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.CourseRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Configuration
public class SchoolCommandLineRunnerTests implements CommandLineRunner {

    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;


    @Override
    public void run(String... args) throws Exception {
        insertData();
    }

    public void insertData() {
        Student student = new Student(null, "Rogerio", null);
        Course course = new Course(null, "Geografia", null);
        Course course1 = new Course(null, "Ciencias", null);
        Course course2 = new Course(null, "Matematica", null);
        Course course3 = new Course(null, "Portugues", null);
        Course course4 = new Course(null, "Historia", null);

        studentRepository.save(student);
        courseRepository.save(course);
        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);

        CourseRegistrationID courseRegistrationID = new CourseRegistrationID(student.getId(), course.getId());
        CourseRegistrationID courseRegistrationID1 = new CourseRegistrationID(student.getId(), course1.getId());
        CourseRegistrationID courseRegistrationID2 = new CourseRegistrationID(student.getId(), course2.getId());
        CourseRegistrationID courseRegistrationID3 = new CourseRegistrationID(student.getId(), course3.getId());
        CourseRegistrationID courseRegistrationID4 = new CourseRegistrationID(student.getId(), course4.getId());

        CourseRegistration courseRegistration = new CourseRegistration(courseRegistrationID, student, course, LocalDateTime.now());
        CourseRegistration courseRegistration1 = new CourseRegistration(courseRegistrationID1, student, course1, LocalDateTime.now());
        CourseRegistration courseRegistration2 = new CourseRegistration(courseRegistrationID2, student, course2, LocalDateTime.now());
        CourseRegistration courseRegistration3 = new CourseRegistration(courseRegistrationID3, student, course3, LocalDateTime.now());
        CourseRegistration courseRegistration4 = new CourseRegistration(courseRegistrationID4, student, course4, LocalDateTime.now());

        courseRegistrationRepository.save(courseRegistration);
        courseRegistrationRepository.save(courseRegistration1);
        courseRegistrationRepository.save(courseRegistration2);
        courseRegistrationRepository.save(courseRegistration3);
        courseRegistrationRepository.save(courseRegistration4);


        course.setStudents(Set.of(courseRegistration));
        course1.setStudents(Set.of(courseRegistration1));
        course2.setStudents(Set.of(courseRegistration2));
        course3.setStudents(Set.of(courseRegistration3));
        course4.setStudents(Set.of(courseRegistration4));
        courseRepository.save(course);
        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);


        List<CourseRegistration> cours = courseRegistrationRepository.findCourseRegistrationsByStudentId(1L);

        for (CourseRegistration c : cours) {
            System.out.println(c.getStudent().getId());
            System.out.println(c.getStudent().getStudentName());
            System.out.println(c.getCourse().getId());
            System.out.println(c.getCourse().getCourseName());

            System.out.println(c.getCourse());
            System.out.println(c.getStudent());

        }
    }
}
