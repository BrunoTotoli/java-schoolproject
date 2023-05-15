package com.bruno.schoolproject.repositories;

import com.bruno.schoolproject.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {



}
