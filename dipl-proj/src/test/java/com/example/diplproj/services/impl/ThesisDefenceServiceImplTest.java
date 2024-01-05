package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.mappers.ThesisDefenceMapper;
import com.example.diplproj.data.models.ThesisDefence;
import com.example.diplproj.data.repositories.ThesisDefenceRepository;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.services.contracts.TeacherService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ThesisDefenceServiceImplTest {

    @Mock
    private ThesisDefenceRepository thesisDefenceRepository;
    @Mock
    private TeacherService teacherService;
    @Mock
    private StudentService studentService;

    @Mock
    private ThesisDefenceMapper thesisDefenceMapper;
    // ... other mocks

    @InjectMocks
    private ThesisDefenceServiceImpl thesisDefenceService;

    private ThesisDefenceCreationDto thesisDefenceCreationDto;
    private ThesisDefence thesisDefence;


}
