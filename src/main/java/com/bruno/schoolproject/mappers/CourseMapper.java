package com.bruno.schoolproject.mappers;

import com.bruno.schoolproject.entities.Course;
import com.bruno.schoolproject.requests.course.CoursePostRequestBody;
import com.bruno.schoolproject.requests.course.CoursePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course coursePostRequestBodyToCourse(CoursePostRequestBody coursePostRequestBody);

    Course coursePutRequestBodyToCourse(CoursePutRequestBody coursePutRequestBody);

}
