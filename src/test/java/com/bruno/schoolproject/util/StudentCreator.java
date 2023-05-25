package com.bruno.schoolproject.util;

import com.bruno.schoolproject.entities.Student;

import java.util.HashSet;

public class StudentCreator {

    public static Student createValidStudent() {
        return new Student(1L, "Rogerio", 19, new HashSet<>());
    }

}
