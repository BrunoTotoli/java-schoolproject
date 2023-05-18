package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.entities.CourseRegistration;
import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.mappers.StudentMapper;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.CourseRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import com.bruno.schoolproject.requests.student.StudentCoursesDTO;
import com.bruno.schoolproject.requests.student.StudentPostRequestBody;
import com.bruno.schoolproject.requests.student.StudentPutRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Student save(StudentPostRequestBody studentPostRequestBody) {
        return studentRepository.save(StudentMapper.INSTANCE.studentPostRequestBodyToStudent(studentPostRequestBody));
    }

    public void replace(StudentPutRequestBody studentPutRequestBody) {
        Student savedStudent = findById(studentPutRequestBody.id());
        Student student = StudentMapper.INSTANCE.studentPutRequestBodyToStudent(studentPutRequestBody);
        studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentCoursesDTO studentWithCourses(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student dont exists"));

        List<CourseRegistration> courseRegistrationsByStudentId = courseRegistrationRepository.findCourseRegistrationsByStudentId(id);

        String studentName = student.getStudentName();
        Integer age = student.getAge();

        List<Course> courses = courseRegistrationsByStudentId.stream()
                .map(x -> x.getCourse())
                .collect(Collectors.toList());
        return new StudentCoursesDTO(id, studentName, age, courses);
    }


}
