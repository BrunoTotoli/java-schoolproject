package com.bruno.schoolproject.repositories;

import com.bruno.schoolproject.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
