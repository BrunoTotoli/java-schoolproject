package com.bruno.schoolproject.requests.teacher;

import com.bruno.schoolproject.entities.Course;

public record TeacherDTO(Long id, String name, Integer age, Course course) {
}
