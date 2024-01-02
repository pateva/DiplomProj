package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.TeacherDto;
import com.example.diplproj.data.mappers.TeacherMapper;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.repositories.TeacherRepository;
import com.example.diplproj.exceptions.UniqueConstraintException;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public Page<TeacherDto> getAllTeachers(int page, int size) {
        Page<Teacher> teacherPage = teacherRepository.findAll(PageRequest.of(page, size));

        return teacherPage.map(teacherMapper::toTeacherDto);
    }

    @Override
    public void createTeacher(TeacherDto teacherDto) {
        if(teacherRepository.existsByEmail(teacherDto.getEmail())) {
            throw new UniqueConstraintException(String.format(Constants.UNIQUE_CONSTRAINT_ERROR_MSG, "email"));
        }

        Teacher teacher = teacherMapper.toTeacher(teacherDto);
        teacherRepository.save(teacher);
    }
}
