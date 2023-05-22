package com.bruno.schoolproject.controllers;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.requests.student.StudentWithCoursesDTO;
import com.bruno.schoolproject.requests.student.StudentPostRequestBody;
import com.bruno.schoolproject.requests.student.StudentPutRequestBody;
import com.bruno.schoolproject.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody StudentPostRequestBody studentPostRequestBody) {
        return new ResponseEntity<>(studentService.save(studentPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<StudentWithCoursesDTO> findStudentWithCourses(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.studentWithCourses(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> replaceStudent(@RequestBody StudentPutRequestBody studentPutRequestBody) {
        studentService.replace(studentPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
