package com.bruno.schoolproject.requests.student;


import com.bruno.schoolproject.entities.Course;

import java.util.List;

public record StudentCoursesDTO(Long id, String studentName, Integer age, List<Course> courseList) {
}
