package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.repositories.CourseRegistrationRepository;
import com.bruno.schoolproject.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRegistrationRepository courseRegistrationRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }


}
