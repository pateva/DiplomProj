package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.TeacherDto;
import com.example.diplproj.data.models.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    Page<TeacherDto> getAllTeachers(int page, int size);
    void createTeacher(TeacherDto teacherDto);
    TeacherDto getTeacherDtoByEmail(String email);
    Teacher getTeacherByEmail(String email);
    void updateTeacher(Long id, TeacherDto teacherDto);
    TeacherDto getById(Long id);
    Teacher getTeacherById(Long id);
    void deleteTeacher(Long id);
    boolean existsById(Long id);
    List<Teacher> getTeachersToDefence(Long defenceId);
}
