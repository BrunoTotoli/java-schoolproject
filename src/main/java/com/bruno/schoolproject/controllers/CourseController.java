package com.bruno.schoolproject.controllers;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/course")
public class CourseController {

    private CourseService courseService;

    @GetMapping
    private ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Course> findById(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Course> save(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.CREATED);
    }

    @PostMapping("/{studentID}/{courseID}")
    private ResponseEntity<Course> saveCourse(@PathVariable Long studentID, @PathVariable Long courseID) {
        return new ResponseEntity<>(courseService.saveStudentOnExistingCourse(studentID, courseID), HttpStatus.OK);
    }

}
