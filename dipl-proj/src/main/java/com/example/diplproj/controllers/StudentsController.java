package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService studentService;

    @HasRoles(Roles.TEACHER)
    @GetMapping
    public ResponseEntity<Page<StudentDto>> getAllStudents(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "25") int size,
                                                           @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(studentService.getAllStudents(page, size), HttpStatus.OK);

    }

    @HasRoles({Roles.STUDENT, Roles.TEACHER})
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable final String id,
                                                 @AuthenticationPrincipal Jwt jwt) {
        if (jwt != null) {
            Map<String, Object> claims = jwt.getClaims();
            System.err.println("Claims: " + claims);
        }
        return new ResponseEntity<>(studentService.getStudentDtoById(Long.parseLong(id)), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody final StudentDto studentDto) {
        studentService.createStudent(studentDto);

        return new ResponseEntity<>(studentService.getStudentDtoByEmail(studentDto.getEmail()), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable final String id, @RequestBody final StudentDto studentDto) {
        studentService.updateStudent(Long.parseLong(id), studentDto);

        return new ResponseEntity<>(studentService.getStudentDtoById(Long.parseLong(id)), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable final String id) {
        studentService.deleteStudent(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //todo getByName list
}
