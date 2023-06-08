package com.bruno.schoolproject.util;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.requests.student.StudentPostRequestBody;

import java.util.HashSet;

public class StudentCreator {

    public static Student createValidStudent() {
        return new Student(1L, "Rogerio", 19, new HashSet<>());
    }

    public static Student createStudentToBeSave() {
        return new Student(null, "Cleito", 22, new HashSet<>());
    }

    public static StudentPostRequestBody createStudentPostRequestBodyToBeSaved() {
        return new StudentPostRequestBody("Rogerio", 19);
    }
}
