package com.bruno.schoolproject.controllers;

import com.bruno.schoolproject.entities.Teacher;
import com.bruno.schoolproject.requests.teacher.TeacherDTO;
import com.bruno.schoolproject.requests.teacher.TeacherPostRequestBody;
import com.bruno.schoolproject.services.TeacherService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> findAll() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(teacherService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> save(@RequestBody TeacherPostRequestBody teacherPostRequestBody) {
        return new ResponseEntity<>(teacherService.save(teacherPostRequestBody), HttpStatus.CREATED);
    }


}
