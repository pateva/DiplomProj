package com.example.diplproj.services.impl;

import com.example.diplproj.clients.Auth0Service;
import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.data.dtos.UserDto;
import com.example.diplproj.data.mappers.StudentMapper;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.repositories.StudentRepository;
import com.example.diplproj.exceptions.UniqueConstraintException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private Auth0Service auth0Service;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void createStudent_whenOK_shouldCreateStudent() {
        StudentDto studentDto = StudentDto
                .builder()
                .email("email@example.com")
                .firstName("John")
                .lastName("Doe")
                .facNumber("facnumber").build();
        Student student = studentMapper.dtoToStudent(studentDto);
        when(studentMapper.dtoToStudent(studentDto)).thenReturn(student);

        studentService.createStudent(studentDto);

        verify(studentRepository, atMostOnce()).existsByEmail(anyString());
        verify(studentRepository, atMostOnce()).existsByFacNumber(anyString());
        verify(auth0Service).createUser(eq(studentDto.getEmail()), any(char[].class), anyList());
        verify(studentRepository).save(student);
    }

    @Test
    void createStudent_NullEmail_ThrowsException() {
        StudentDto studentDto = StudentDto
                .builder()
                .email(null)
                .firstName("John")
                .lastName("Doe")
                .facNumber("facnumber").build();

        assertThrows(UniqueConstraintException.class, () -> studentService.createStudent(studentDto));

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void createStudent_DuplicateEmail_ThrowsException() {
        StudentDto studentDto = StudentDto
                .builder()
                .email("example@abv.bg")
                .firstName("John")
                .lastName("Doe")
                .facNumber("facnumber").build();

        when(studentRepository.existsByEmail(studentDto.getEmail())).thenReturn(true);

        assertThrows(UniqueConstraintException.class, () -> studentService.createStudent(studentDto));

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void createStudent_NullFacNumber_ThrowsException() {
        StudentDto studentDto = StudentDto
                .builder()
                .email("example@abv.bg")
                .firstName("John")
                .lastName("Doe")
                .facNumber(null).build();
        assertThrows(UniqueConstraintException.class, () -> studentService.createStudent(studentDto));

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void createStudent_DuplicateFacNumber_ThrowsException() {
        StudentDto studentDto = StudentDto
                .builder()
                .email("example@abv.bg")
                .firstName("John")
                .lastName("Doe")
                .facNumber("facnum").build();

        when(studentRepository.existsByFacNumber(studentDto.getFacNumber())).thenReturn(true);

        assertThrows(UniqueConstraintException.class, () -> studentService.createStudent(studentDto));

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void updateStudent_Success() {
        Long studentId = 1L;
        StudentDto studentDto = StudentDto
                .builder()
                .email("example@abv.bg")
                .firstName("John")
                .lastName("Doe")
                .facNumber("facnum").build();
        Student student = Student
                .builder()
                .email("example@abv.bg")
                .firstName("John")
                .lastName("Doe")
                .facNumber("facnum").build();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        studentService.updateStudent(studentId, studentDto);

        verify(studentRepository).save(student);
        assertEquals("example@abv.bg", student.getEmail());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
    }

}