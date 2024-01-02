package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.data.models.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StudentMapper {
    Student dtoToStudent(StudentDto studentDto);
    StudentDto studentToDto(Student student);
}
