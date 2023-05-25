package com.bruno.schoolproject.util;

import com.bruno.schoolproject.requests.course.CoursePostRequestBody;

public class CoursePostRequestBodyCreator {

    public static CoursePostRequestBody createCoursePostRequestBody() {
        return new CoursePostRequestBody("History");
    }

}
