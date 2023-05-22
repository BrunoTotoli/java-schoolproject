package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.*;
import com.bruno.schoolproject.mappers.CourseMapper;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.CourseRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import com.bruno.schoolproject.repositories.TeacherRepository;
import com.bruno.schoolproject.requests.course.CoursePostRequestBody;
import com.bruno.schoolproject.requests.course.CoursePutRequestBody;
import com.bruno.schoolproject.requests.course.CourseWithStudentsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Course save(CoursePostRequestBody coursePostRequestBody) {
        return courseRepository.save(CourseMapper.INSTANCE.coursePostRequestBodyToCourse(coursePostRequestBody));
    }

    public void replace(CoursePutRequestBody coursePutRequestBody) {
        Course savedCourse = findById(coursePutRequestBody.id());
        Course course = CourseMapper.INSTANCE.coursePutRequestBodyToCourse(coursePutRequestBody);
        courseRepository.save(course);
    }

    public void delete(Long id) {
        Course courseToDelete = findById(id);
        courseRepository.delete(courseToDelete);
    }

    @Transactional
    public Course saveStudentOnExistingCourse(Long studentId, Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course dont exists"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student dont exists"));

        CourseRegistrationID courseRegistrationID =
                new CourseRegistrationID(studentId, courseId);

        CourseRegistration courseRegistration =
                new CourseRegistration(courseRegistrationID, student, course, LocalDateTime.now());

        courseRegistrationRepository
                .save(courseRegistration);

        Set<CourseRegistration> ratingsCourse = courseRegistration
                .getCourse()
                .getStudents();

        ratingsCourse.add(courseRegistration);

        course.setStudents(ratingsCourse);

        return courseRepository.save(course);
    }


    public CourseWithStudentsDTO findCourseWithStudentsById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course dont exists"));

        List<CourseRegistration> courseRegistration = courseRegistrationRepository
                .findCourseRegistrationsByCourseId(id);

        List<Student> studentList = courseRegistration.stream()
                .map(x -> x.getStudent())
                .collect(Collectors.toList());

        return new CourseWithStudentsDTO(course.getId(),
                course.getCourseName(),
                course.getTeacher(),
                studentList);
    }

    @Transactional
    public Course saveTeacherOnExistingCourse(Long courseId, Long teacherId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course dont exists"));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher dont exists"));

        course.setTeacher(teacher);

        teacher.setCourse(course);
        teacherRepository.save(teacher);

        return courseRepository.save(course);
    }
}
