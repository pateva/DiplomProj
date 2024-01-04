package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.data.models.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface StudentService {
    void createStudent(StudentDto studentDto);
    StudentDto getStudentDtoByEmail(String email);
    Student getStudentByEmail(String email);
    StudentDto getStudentDtoById(Long id);
    Student getStudentById(Long id);
    Page<StudentDto> getAllStudents(int page, int size);
    void updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
   List<Student> getAllByIds(Iterable<Long> ids);
}
