package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.entities.Teacher;
import com.bruno.schoolproject.repositories.TeacherRepository;
import com.bruno.schoolproject.requests.teacher.TeacherDTO;
import com.bruno.schoolproject.requests.teacher.TeacherPostRequestBody;
import com.bruno.schoolproject.util.CourseCreator;
import com.bruno.schoolproject.util.TeacherCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(teacherRepository.findAll())
                .thenReturn(List.of(TeacherCreator.createValidTeacher()));
        BDDMockito.when(teacherRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TeacherCreator.createValidTeacher()));
        BDDMockito.when(teacherRepository.save(ArgumentMatchers.any()))
                .thenReturn(TeacherCreator.createValidTeacher());
    }


    @Test
    void findAll() {
        String expectedCourseName = TeacherCreator.createValidTeacher().getName();
        long expectedCourseId = TeacherCreator.createValidTeacher().getId();

        List<Teacher> teachers = teacherService.findAll();

        Assertions.assertThat(teachers)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(teachers.get(0).getName())
                .isEqualTo(expectedCourseName);

        Assertions.assertThat(teachers.get(0).getId())
                .isEqualTo(expectedCourseId);
    }

    @Test
    void findById() {
        Teacher expectedTeacher = TeacherCreator.createValidTeacher();

        TeacherDTO teacher = teacherService.findById(1L);

        Assertions.assertThat(teacher)
                .isNotNull();
        Assertions.assertThat(teacher.name())
                .isEqualTo(expectedTeacher.getName());
        Assertions.assertThat(teacher.id())
                .isEqualTo(expectedTeacher.getId());
        Assertions.assertThat(teacher.age())
                .isEqualTo(expectedTeacher.getAge());
        Assertions.assertThat(teacher.course())
                .isEqualTo(expectedTeacher.getCourse());

    }

    @Test
    void save() {
        TeacherPostRequestBody expectedPostRequestTeacher = TeacherCreator.createPostRequestBodyValid();

        Teacher savedTeacher = teacherService.save(TeacherCreator.createPostRequestBodyValid());

        Assertions.assertThat(expectedPostRequestTeacher.name())
                .isEqualTo(savedTeacher.getName());
        Assertions.assertThat(expectedPostRequestTeacher.age())
                .isEqualTo(savedTeacher.getAge());
        Assertions.assertThat(savedTeacher.getId())
                .isNotNull();
    }
}