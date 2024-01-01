package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.StudentDto;

public interface StudentService {
    void createStudent(StudentDto studentDto);
    StudentDto getStudentByEmail(String email);
}
