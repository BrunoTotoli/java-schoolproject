package com.bruno.schoolproject.repositories;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.util.StudentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    void save_PersistsStudent_WhenSuccessful() {
        Student studentToBeSave = StudentCreator.createStudentToBeSave();

        Student savedStudent = studentRepository.save(studentToBeSave);

        Assertions.assertThat(savedStudent)
                .isNotNull();

        Assertions.assertThat(savedStudent.getId())
                .isNotNull();

        Assertions.assertThat(savedStudent.getStudentName())
                .isEqualTo(studentToBeSave.getStudentName());

    }

    @Test
    void save_UpdateStudent_WhenSuccessful() {
        Student studentToBeSave = StudentCreator.createStudentToBeSave();

        Student savedStudent = studentRepository.save(studentToBeSave);

        savedStudent.setStudentName("new name");

        Student updatedStudent = studentRepository.save(savedStudent);

        Assertions.assertThat(savedStudent)
                .isNotNull();

        Assertions.assertThat(updatedStudent.getId())
                .isNotNull();

        Assertions.assertThat(updatedStudent.getStudentName())
                .isEqualTo(studentToBeSave.getStudentName());

    }

    @Test
    void delete_DeleteStudent_WhenSuccessful() {
        Student studentToBeSave = StudentCreator.createStudentToBeSave();

        Student savedStudent = studentRepository.save(studentToBeSave);

        studentRepository.delete(savedStudent);

        Optional<Student> optionalStudent = studentRepository.findById(savedStudent.getId());

        Assertions.assertThat(optionalStudent)
                .isEmpty();
    }
}