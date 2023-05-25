package com.bruno.schoolproject.util;

import com.bruno.schoolproject.entities.Teacher;

public class TeacherCreator {

    public static Teacher createValidTeacher() {
        return new Teacher(1L, "Roger", 22, null);
    }

    public static Teacher createTeacherWithCourse() {
        return new Teacher(1L, "Roger", 22, CourseCreator.createValidCourse());
    }

}
