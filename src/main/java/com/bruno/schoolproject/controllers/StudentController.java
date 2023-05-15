package com.bruno.schoolproject.controllers;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping
    private ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    private ResponseEntity<Student> findById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Student> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }


}
