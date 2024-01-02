package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.TeacherDto;
import org.springframework.data.domain.Page;

public interface TeacherService {
    Page<TeacherDto> getAllTeachers(int page, int size);
    void createTeacher(TeacherDto teacherDto);
}
