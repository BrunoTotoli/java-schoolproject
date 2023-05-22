package com.bruno.schoolproject.mappers;

import com.bruno.schoolproject.entities.Teacher;
import com.bruno.schoolproject.requests.teacher.TeacherDTO;
import com.bruno.schoolproject.requests.teacher.TeacherPostRequestBody;
import com.bruno.schoolproject.requests.teacher.TeacherPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    Teacher teacherPostRequestBodyToTeacher(TeacherPostRequestBody teacherPostRequestBody);

    Teacher teacherPutRequestBodyToTeacher(TeacherPutRequestBody teacherPutRequestBodyToTeacher);

    TeacherDTO teacherToTeacherDTO(Teacher teacher);
}
