package com.bruno.schoolproject.util;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.entities.CourseRegistration;
import com.bruno.schoolproject.entities.CourseRegistrationID;
import com.bruno.schoolproject.requests.course.CoursePostRequestBody;
import com.bruno.schoolproject.requests.course.CoursePutRequestBody;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CourseCreator {

    public static Course createCourseToBeSaved() {
        return Course.builder()
                .courseName("History")
                .build();
    }


    public static Course createValidCourse() {
        return Course.builder()
                .courseName("History")
                .id(1L)
                .students(new HashSet<>())
                .build();
    }

    public static Course createValidCourseWithStudents() {
        return Course.builder()
                .courseName("History")
                .id(1L)
                .students(Set.of(new CourseRegistration(new CourseRegistrationID(1L, 1L), StudentCreator.createValidStudent(), CourseCreator.createValidCourse()
                        , LocalDateTime.now())))
                .build();
    }

    public static Course createValidCourseWithTeacher() {
        return Course.builder()
                .courseName("History")
                .id(1L)
                .teacher(TeacherCreator.createValidTeacher())
                .build();

    }

    public static CoursePostRequestBody createCoursePostRequestBodyToBeSaved() {
        return new CoursePostRequestBody("History");
    }

    public static CoursePutRequestBody createCoursePutRequestBody() {
        return new CoursePutRequestBody(1L, "History");
    }

}
