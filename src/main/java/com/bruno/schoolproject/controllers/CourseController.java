package com.bruno.schoolproject.controllers;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.requests.course.CoursePostRequestBody;
import com.bruno.schoolproject.requests.course.CoursePutRequestBody;
import com.bruno.schoolproject.requests.course.CourseStudentsDTO;
import com.bruno.schoolproject.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody CoursePostRequestBody coursePostRequestBody) {
        return new ResponseEntity<>(courseService.save(coursePostRequestBody), HttpStatus.CREATED);
    }

    @PostMapping("/link")
    public ResponseEntity<Course> saveStudentOnCourse(@RequestParam Long studentID, @RequestParam Long courseID) {
        return new ResponseEntity<>(courseService.saveStudentOnExistingCourse(studentID, courseID), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<CourseStudentsDTO> findCourseWithStudentsById(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.findCourseWithStudentsById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody CoursePutRequestBody coursePutRequestBody) {
        courseService.replace(coursePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
