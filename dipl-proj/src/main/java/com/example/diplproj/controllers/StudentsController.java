package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.services.contracts.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService studentService;
    @PostMapping
    public StudentDto createStudent(@RequestBody final StudentDto studentDto) {
        studentService.createStudent(studentDto);

        return studentService.getStudentByEmail(studentDto.getEmail());
    }
}
