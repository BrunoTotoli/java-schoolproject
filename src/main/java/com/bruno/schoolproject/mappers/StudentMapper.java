package com.bruno.schoolproject.mappers;

import com.bruno.schoolproject.entities.Student;
import com.bruno.schoolproject.requests.student.StudentPostRequestBody;
import com.bruno.schoolproject.requests.student.StudentPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student studentPostRequestBodyToStudent(StudentPostRequestBody studentPostRequestBody);

    Student studentPutRequestBodyToStudent(StudentPutRequestBody studentPutRequestBody);



}
