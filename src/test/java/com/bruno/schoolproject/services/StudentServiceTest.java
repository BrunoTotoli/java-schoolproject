package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import com.bruno.schoolproject.requests.student.StudentPostRequestBody;
import com.bruno.schoolproject.util.StudentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;

    @InjectMocks
    private StudentService studentService;


    @BeforeEach
    void setUp() {
        BDDMockito.when(studentRepository.findAll())
                .thenReturn(List.of(StudentCreator.createValidStudent()));
        BDDMockito.when(studentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(StudentCreator.createValidStudent()));
        BDDMockito.when(studentRepository.save(ArgumentMatchers.any(Student.class)))
                .thenReturn(StudentCreator.createValidStudent());
    }

    @Test
    void findAll() {
        Student student = StudentCreator.createValidStudent();

        List<Student> studentList = studentService.findAll();

        Assertions.assertThat(studentList)
                .isNotEmpty()
                .isNotNull()
                .contains(student)
                .hasSize(1);
    }

    @Test
    void findById() {
        Student student = StudentCreator.createValidStudent();

        Student studentFindById = studentService.findById(1L);

        Assertions.assertThat(studentFindById)
                .isNotNull();

        Assertions.assertThat(studentFindById.getId())
                .isNotNull()
                .isEqualTo(student.getId());
    }

    @Test
    void save() {
        StudentPostRequestBody studentPostRequestBody = StudentCreator.createStudentPostRequestBodyToBeSaved();

        Student savedStudent = studentService.save(studentPostRequestBody);

        Assertions.assertThat(savedStudent)
                .isNotNull();

        Assertions.assertThat(savedStudent.getId())
                .isNotNull();

        Assertions.assertThat(savedStudent.getStudentName())
                .isNotNull()
                .isEqualTo(studentPostRequestBody.studentName());

    }

    @Test
    void replace() {
    }

    @Test
    void delete() {
    }

    @Test
    void studentWithCourses() {
    }
}