package com.bruno.schoolproject.util;

import com.bruno.schoolproject.requests.course.CoursePutRequestBody;

public class CoursePutRequestBodyCreator {

    public static CoursePutRequestBody createCoursePutRequestBody() {
        return new CoursePutRequestBody(1L, "History");
    }

}
