package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.TeacherDto;
import com.example.diplproj.data.models.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TeacherMapper {
    TeacherDto toTeacherDto(Teacher teacher);
    Teacher toTeacher(TeacherDto teacherDto);
}
