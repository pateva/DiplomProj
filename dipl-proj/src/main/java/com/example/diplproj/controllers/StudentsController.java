package com.example.diplproj.controllers;

import com.example.diplproj.clients.Auth0ServiceImpl;
import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.utils.annotations.HasRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService studentService;
    private final Auth0ServiceImpl authService;

    @GetMapping
    public ResponseEntity<Page<StudentDto>> getAllStudents(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "25") int size,
                                                           @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(studentService.getAllStudents(page, size), HttpStatus.OK);

    }

    @HasRoles("teacher")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable final String id,
                                                 @AuthenticationPrincipal Jwt jwt) {
        if (jwt != null) {
            Map<String, Object> claims = jwt.getClaims();
            System.err.println("Claims: " + claims);
        }
        return new ResponseEntity<>(studentService.getStudentById(Long.parseLong(id)), HttpStatus.OK);
    }

    @HasRoles("teacher")
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody final StudentDto studentDto) {
        studentService.createStudent(studentDto);

        return new ResponseEntity<>(studentService.getStudentByEmail(studentDto.getEmail()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable final String id, @RequestBody final StudentDto studentDto) {
        studentService.updateStudent(Long.parseLong(id), studentDto);

        return new ResponseEntity<>(studentService.getStudentById(Long.parseLong(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable final String id) {
        studentService.deleteStudent(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
