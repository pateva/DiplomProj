package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.data.mappers.StudentMapper;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.repositories.StudentRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.utils.Constants;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public StudentDto getStudentById(Long id) {

        return studentMapper.studentToDto(getStudent(id));
    }

    @Override
    public Page<StudentDto> getAllStudents(int page, int size) {
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(page, size));

        return studentPage.map(studentMapper::studentToDto);
    }

    @Override
    public void updateStudent(Long id, StudentDto studentDto) {
        Student student = getStudent(id);

        student.setFacNumber(studentDto.getFirstName())
                .setLastName(studentDto.getLastName())
                .setEmail(studentDto.getEmail());

        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudent(id);
        studentRepository.delete(student);
    }

    private Student getStudent(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);

        if (studentOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Student", "this id"));
        }

        return studentOpt.get();
    }
}
