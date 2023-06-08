package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.entities.Teacher;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.CourseRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import com.bruno.schoolproject.repositories.TeacherRepository;
import com.bruno.schoolproject.requests.course.CoursePostRequestBody;
import com.bruno.schoolproject.requests.course.CourseWithStudentsDTO;
import com.bruno.schoolproject.util.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;
    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private CourseService courseServiceMock;


    @BeforeEach
    void setUp() {
        BDDMockito.when(courseRepository.findAll())
                .thenReturn(List.of(CourseCreator.createValidCourse()));
        BDDMockito.when(courseRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CourseCreator.createValidCourse()));
        BDDMockito.when(courseRepository.save(ArgumentMatchers.any()))
                .thenReturn(CourseCreator.createValidCourse());
        BDDMockito.when(studentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(StudentCreator.createValidStudent()));
        BDDMockito.when(courseRegistrationRepository.save(ArgumentMatchers.any()))
                .thenReturn(CourseRegistrationCreator.createValidCourseRegistration());
        BDDMockito.when(teacherRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TeacherCreator.createValidTeacher()));
        BDDMockito.when(teacherRepository.save(ArgumentMatchers.any()))
                .thenReturn(TeacherCreator.createTeacherWithCourse());
        BDDMockito.when(courseRegistrationRepository.findCourseRegistrationsByCourseId(ArgumentMatchers.anyLong()))
                .thenReturn(CourseRegistrationCreator.courseRegistrationList());

    }

    @Test
    @DisplayName("findAll returns list of courses without students when successful")
    void findAll_ReturnListOfCoursesWithoutStudents_WhenSuccessful() {
        String expectedCourseName = CourseCreator.createValidCourse().getCourseName();
        long expectedCourseId = CourseCreator.createValidCourse().getId();

        List<Course> courses = courseServiceMock.findAll();

        Assertions.assertThat(courses)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(courses.get(0).getCourseName())
                .isEqualTo(expectedCourseName);

        Assertions.assertThat(courses.get(0).getId())
                .isEqualTo(expectedCourseId);

        Assertions.assertThat(courses.get(0).getStudents())
                .isEmpty();
    }

    @Test
    void findById_ReturnCourse_WhenSuccessful() {
        long expectedId = CourseCreator.createValidCourse().getId();
        Course course = courseServiceMock.findById(1L);

        Assertions.assertThat(course)
                .isNotNull();

        Assertions.assertThat(course.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    void findById_ThrowException_WhenCourseNotFound() {
        BDDMockito.when(courseRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> courseServiceMock.findById(1L));
    }


    @Test
    void saveStudentOnExistingCourse() {
        BDDMockito.when(courseRepository.save(ArgumentMatchers.any()))
                .thenReturn(CourseCreator.createValidCourseWithStudents());

        Course course = courseServiceMock.saveStudentOnExistingCourse(1L, 1L);


        Assertions.assertThat(course).isNotNull();

        Assertions.assertThat(course.getStudents()).isNotNull();
    }

    @Test
    void saveStudentOnExistingCourse_throwException() {
        BDDMockito.when(courseRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CourseCreator.createValidCourseWithStudents()));
        BDDMockito.when(courseRegistrationRepository.existsCourseRegistrationsByStudentIdAndCourseId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(true);


        Assertions.assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> this.courseServiceMock.saveStudentOnExistingCourse(1L, 1L));


    }

    @Test
    void findCourseWithStudentsById() {
        CourseWithStudentsDTO course = courseServiceMock.findCourseWithStudentsById(1L);

        Student studentInsideList = StudentCreator.createValidStudent();

        Assertions.assertThat(course).isNotNull();
        Assertions.assertThat(course.id()).isNotNull();
        Assertions.assertThat(course.studentList()).isNotNull().contains(studentInsideList);


    }

    @Test
    void saveTeacherOnExistingCourse() {
        BDDMockito.when(courseRepository.save(ArgumentMatchers.any()))
                .thenReturn(CourseCreator.createValidCourseWithTeacher());

        Course course = courseServiceMock.saveTeacherOnExistingCourse(1L, 1L);

        Teacher teacher = TeacherCreator.createValidTeacher();

        Assertions.assertThat(course)
                .isNotNull();
        Assertions.assertThat(course.getTeacher())
                .isNotNull();
        Assertions.assertThat(course.getTeacher().getName())
                .isEqualTo(teacher.getName());
        Assertions.assertThat(course.getTeacher().getId())
                .isEqualTo(teacher.getId());


    }

    @Test
    void save_PersistsCourse_WhenSuccessful() {
        CoursePostRequestBody courseToBeSaved =
               CourseCreator.createCoursePostRequestBodyToBeSaved();

        Course savedCourse = courseServiceMock.save(courseToBeSaved);

        Assertions.assertThat(courseToBeSaved.courseName())
                .isNotNull();

        Assertions.assertThat(savedCourse)
                .isNotNull();

        Assertions.assertThat(savedCourse.getId())
                .isNotNull();

        Assertions.assertThat(savedCourse.getCourseName())
                .isEqualTo(courseToBeSaved.courseName());

    }

    @Test
    void save_UpdateCourse_WhenSuccessful() {
        Assertions.assertThatCode(() -> courseServiceMock.replace(CourseCreator.createCoursePutRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    void delete_DeleteCourse_WhenSuccessful() {
        Assertions.assertThatCode(() -> courseServiceMock.delete(1L))
                .doesNotThrowAnyException();
    }
}