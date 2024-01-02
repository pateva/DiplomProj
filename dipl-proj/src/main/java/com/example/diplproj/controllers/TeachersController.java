package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.TeacherDto;
import com.example.diplproj.services.contracts.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<Page<TeacherDto>> getAllTeachers(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "25") int size,
                                                           @AuthenticationPrincipal User user) {

        return new ResponseEntity<>(teacherService.getAllTeachers(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody final TeacherDto teacherDto) {
        return null;
    }
}
