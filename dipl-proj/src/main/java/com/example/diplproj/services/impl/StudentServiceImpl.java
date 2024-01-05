package com.example.diplproj.services.impl;

import com.example.diplproj.clients.Auth0Service;
import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.data.mappers.StudentMapper;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.repositories.StudentRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.exceptions.UniqueConstraintException;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.utils.Constants;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final Auth0Service auth0Service;

    @Override
    public void createStudent(StudentDto studentDto) {
        if (studentDto.getEmail() == null || studentRepository.existsByEmail(studentDto.getEmail())) {
            throw new UniqueConstraintException(Constants.INVALID_EMAIL_ERROR_MSG);
        }

        if (studentDto.getFacNumber() == null || studentRepository.existsByFacNumber(studentDto.getFacNumber())) {
            throw new UniqueConstraintException(Constants.INVALID_FAC_NUMBER);
        }

        auth0Service.createUser(studentDto.getEmail(), studentDto.getLastName().concat("A123!").toCharArray(), List.of(Roles.STUDENT.getVal()));
        studentRepository.save(studentMapper.dtoToStudent(studentDto));
    }

    @Override
    public StudentDto getStudentDtoByEmail(String email) {

        return studentMapper.studentToDto(getStudentByEmail(email));
    }

    @Override
    public Student getStudentByEmail(String email) {
        Optional<Student> studentOpt = studentRepository.findByEmail(email);

        if (studentOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Student", "email address"));
        }

        return studentOpt.get();
    }

    @Override
    public StudentDto getStudentDtoById(Long id) {

        return studentMapper.studentToDto(getStudentById(id));
    }

    @Override
    public Page<StudentDto> getAllStudents(int page, int size) {
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(page, size));

        return studentPage.map(studentMapper::studentToDto);
    }

    @Override
    public void updateStudent(Long id, StudentDto studentDto) {
        Student student = getStudentById(id);

        student.setFirstName(studentDto.getFirstName())
                .setLastName(studentDto.getLastName());

        if (!student.getEmail().equals(studentDto.getEmail())) {
            if (studentDto.getEmail() == null || studentRepository.existsByEmail(studentDto.getEmail())) {
                throw new UniqueConstraintException(Constants.INVALID_EMAIL_ERROR_MSG);
            }

            auth0Service.updateUserEmail(student.getEmail(), studentDto.getEmail());
            student.setEmail(studentDto.getEmail());
        }

        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);

        auth0Service.deleteUser(student.getEmail());
        studentRepository.delete(student);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);

        if (studentOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Student", "this id"));
        }

        return studentOpt.get();
    }
}
