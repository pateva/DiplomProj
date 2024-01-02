package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.StudentDto;
import org.springframework.data.domain.Page;

public interface StudentService {
    void createStudent(StudentDto studentDto);
    StudentDto getStudentByEmail(String email);
    StudentDto getStudentById(Long id);
    Page<StudentDto> getAllStudents(int page, int size);
    void updateStudent(Long id, StudentDto studentDto);
}
