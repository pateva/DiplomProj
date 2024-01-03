package com.example.diplproj.controllers;

import com.example.diplproj.data.dtos.TeacherDto;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.utils.annotations.HasRoles;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersController {
    private final TeacherService teacherService;

    @HasRoles(Roles.TEACHER)
    @GetMapping
    public ResponseEntity<Page<TeacherDto>> getAllTeachers(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "25") int size) {

        return new ResponseEntity<>(teacherService.getAllTeachers(page, size), HttpStatus.OK);
    }

    @HasRoles({Roles.TEACHER, Roles.STUDENT})
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable final Long id) {

        return new ResponseEntity<>(teacherService.getById(id), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody final TeacherDto teacherDto) {
        teacherService.createTeacher(teacherDto);

        return new ResponseEntity<>(teacherService.getTeacherDtoByEmail(teacherDto.getEmail()), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable final Long id, @RequestBody final TeacherDto teacherDto) {
        teacherService.updateTeacher(id, teacherDto);

        return new ResponseEntity<>(teacherService.getById(id), HttpStatus.OK);
    }

    @HasRoles(Roles.TEACHER)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable final Long id) {
        teacherService.deleteTeacher(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
