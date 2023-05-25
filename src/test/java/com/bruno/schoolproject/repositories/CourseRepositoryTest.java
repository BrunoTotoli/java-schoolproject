package com.bruno.schoolproject.repositories;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.util.CourseCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void save_PersistsCourse_WhenSuccessful() {
        Course courseToBeSaved = CourseCreator.createCourseToBeSaved();

        Course savedCourse = courseRepository.save(courseToBeSaved);

        Assertions.assertThat(savedCourse)
                .isNotNull();

        Assertions.assertThat(savedCourse.getId())
                .isNotNull();

        Assertions.assertThat(savedCourse.getCourseName())
                .isEqualTo(courseToBeSaved.getCourseName());

    }

    @Test
    void save_UpdateCourse_WhenSuccessful() {
        Course courseToBeSaved = CourseCreator.createCourseToBeSaved();

        Course savedCourse = courseRepository.save(courseToBeSaved);

        savedCourse.setCourseName("new course");

        Course updatedCourse = courseRepository.save(savedCourse);

        Assertions.assertThat(savedCourse)
                .isNotNull();

        Assertions.assertThat(updatedCourse.getId())
                .isNotNull();

        Assertions.assertThat(updatedCourse.getCourseName())
                .isEqualTo(courseToBeSaved.getCourseName());

    }

    @Test
    void delete_DeleteCourse_WhenSuccessful(){
        Course courseToBeSaved = CourseCreator.createCourseToBeSaved();

        Course savedCourse = courseRepository.save(courseToBeSaved);

        courseRepository.delete(savedCourse);

        Optional<Course> optionalCourse = courseRepository.findById(savedCourse.getId());

        Assertions.assertThat(optionalCourse)
                .isEmpty();
    }

}