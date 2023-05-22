package com.bruno.schoolproject.services;

import com.bruno.schoolproject.entities.Teacher;
import com.bruno.schoolproject.mappers.TeacherMapper;
import com.bruno.schoolproject.repositories.TeacherRepository;
import com.bruno.schoolproject.requests.teacher.TeacherDTO;
import com.bruno.schoolproject.requests.teacher.TeacherPostRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public TeacherDTO findById(Long id) {
        return TeacherMapper.INSTANCE.teacherToTeacherDTO(teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Teacher dont exists")));
    }

    public Teacher save(TeacherPostRequestBody teacherPostRequestBody) {
        return teacherRepository.save(TeacherMapper.INSTANCE.teacherPostRequestBodyToTeacher(teacherPostRequestBody));
    }

}
