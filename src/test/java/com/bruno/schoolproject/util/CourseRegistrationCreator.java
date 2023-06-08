package com.bruno.schoolproject.util;

import com.bruno.schoolproject.entities.CourseRegistration;
import com.bruno.schoolproject.entities.CourseRegistrationID;

import java.time.LocalDateTime;
import java.util.List;

public class CourseRegistrationCreator {

    public static List<CourseRegistration> courseRegistrationList() {
        return List.of(new CourseRegistration(new CourseRegistrationID(1L, 1L)
                , StudentCreator.createValidStudent(),
                CourseCreator.createValidCourse(),
                LocalDateTime.now()));
    }

    public static CourseRegistration createValidCourseRegistration() {
        return new CourseRegistration(new CourseRegistrationID(1L, 1L)
                , StudentCreator.createValidStudent()
                , CourseCreator.createValidCourseWithStudents()
                , LocalDateTime.now());
    }

}
