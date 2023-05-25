package com.bruno.schoolproject.repositories;

import com.bruno.schoolproject.entities.CourseRegistration;
import com.bruno.schoolproject.entities.CourseRegistrationID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, CourseRegistrationID> {

    List<CourseRegistration> findCourseRegistrationsByStudentId(Long id);

    List<CourseRegistration> findCourseRegistrationsByCourseId(Long id);

    boolean existsCourseRegistrationsByStudentIdAndCourseId(Long studentId, Long courseId);

}
