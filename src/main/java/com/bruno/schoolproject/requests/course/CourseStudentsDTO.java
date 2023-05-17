package com.bruno.schoolproject.requests.course;

import com.bruno.schoolproject.entities.Student;

import java.util.List;

public record CourseStudentsDTO(Long id, String courseName, List<Student> studentList) {
}
