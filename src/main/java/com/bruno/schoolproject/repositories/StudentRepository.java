package com.bruno.schoolproject.repositories;

import com.bruno.schoolproject.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
