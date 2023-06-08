package com.bruno.schoolproject.repositories;

import com.bruno.schoolproject.entities.Teacher;
import com.bruno.schoolproject.util.TeacherCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    void save_PersistsTeacher_WhenSuccessful() {
        Teacher teacherToBeSaved = TeacherCreator.createTeacherToBeSaved();

        Teacher savedTeacher = teacherRepository.save(teacherToBeSaved);

        Assertions.assertThat(savedTeacher)
                .isNotNull();

        Assertions.assertThat(savedTeacher.getId())
                .isNotNull();

        Assertions.assertThat(savedTeacher.getName())
                .isEqualTo(teacherToBeSaved.getName());

    }

    @Test
    void save_UpdateTeacher_WhenSuccessful() {
        Teacher teacherToBeSave = TeacherCreator.createTeacherToBeSaved();

        Teacher savedTeacher = teacherRepository.save(teacherToBeSave);

        savedTeacher.setName("new name");

        Teacher updatedTeacher = teacherRepository.save(savedTeacher);

        Assertions.assertThat(savedTeacher)
                .isNotNull();

        Assertions.assertThat(updatedTeacher.getId())
                .isNotNull();

        Assertions.assertThat(updatedTeacher.getName())
                .isEqualTo(teacherToBeSave.getName());

    }

    @Test
    void delete_DeleteTeacher_WhenSuccessful() {
        Teacher teacherToBeSaved = TeacherCreator.createTeacherToBeSaved();

        Teacher savedTeacher = teacherRepository.save(teacherToBeSaved);

        teacherRepository.delete(savedTeacher);

        Optional<Teacher> optionalTeacher = teacherRepository.findById(savedTeacher.getId());

        Assertions.assertThat(optionalTeacher)
                .isEmpty();
    }

}