package com.bruno.schoolproject.util;

import com.bruno.schoolproject.entities.Teacher;
import com.bruno.schoolproject.requests.teacher.TeacherPostRequestBody;

public class TeacherCreator {

    public static Teacher createValidTeacher() {
        return new Teacher(1L, "Roger", 22, null);
    }

    public static Teacher createTeacherWithCourse() {
        return new Teacher(1L, "Roger", 22, CourseCreator.createValidCourse());
    }

    public static Teacher createTeacherToBeSaved() {
        return new Teacher(null, "Rog", 22, null);
    }

    public static TeacherPostRequestBody createPostRequestBodyValid() {
        return new TeacherPostRequestBody("Roger", 22);
    }
}
