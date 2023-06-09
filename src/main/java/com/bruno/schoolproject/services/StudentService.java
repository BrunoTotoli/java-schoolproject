package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.entities.CourseRegistration;
import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.mappers.StudentMapper;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import com.bruno.schoolproject.requests.student.StudentPostRequestBody;
import com.bruno.schoolproject.requests.student.StudentPutRequestBody;
import com.bruno.schoolproject.requests.student.StudentWithCoursesDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
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

    public StudentWithCoursesDTO studentWithCourses(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student dont exists"));

        List<CourseRegistration> courseRegistrationsByStudentId = courseRegistrationRepository
                .findCourseRegistrationsByStudentId(id);

        List<Course> courses = courseRegistrationsByStudentId.stream()
                .map(CourseRegistration::getCourse)
                .toList();
        return new StudentWithCoursesDTO(id, student.getStudentName(), student.getAge(), courses);
    }


}
