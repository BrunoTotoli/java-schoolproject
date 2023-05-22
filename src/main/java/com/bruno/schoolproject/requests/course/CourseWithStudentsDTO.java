package com.bruno.schoolproject.requests.course;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.entities.Teacher;

import java.util.List;

public record CourseWithStudentsDTO(Long id, String courseName, Teacher teacher, List<Student> studentList) {
}
