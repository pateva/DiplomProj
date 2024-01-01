package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.data.mappers.StudentMapper;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.repositories.StudentRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);

        if (student == null) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Student", "email address"));
        }

        return studentMapper.studentToDto(student);
    }
}
