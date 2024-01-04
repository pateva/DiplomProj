package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.mappers.ThesisMapper;
import com.example.diplproj.data.models.Thesis;
import com.example.diplproj.data.models.ThesisApplication;
import com.example.diplproj.data.repositories.ThesisRepository;
import com.example.diplproj.exceptions.UniqueConstraintException;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.services.contracts.ThesisApplicationService;
import com.example.diplproj.services.contracts.ThesisService;
import com.example.diplproj.utils.Constants;
import com.example.diplproj.utils.enums.ApplicationStatus;
import com.example.diplproj.utils.enums.ThesisStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisServiceImpl implements ThesisService {
    private final ThesisRepository thesisRepository;
    private final ThesisMapper thesisMapper;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ThesisApplicationService thesisApplicationService;

    @Override
    public void createThesis(ThesisCreationDto thesisCreationDto, String studentEmail) {
        Thesis thesis = thesisMapper.toThesisFromCreationDto(thesisCreationDto);
        thesis.setTeacher(teacherService.getTeacherById(thesisCreationDto.getTeacherId()));
        thesis.setStudent(studentService.getStudentByEmail(studentEmail));
        ThesisApplication thesisApplication = thesisApplicationService.getThesisApplicationById(thesisCreationDto.getThesisApplicationId());

        if(thesisApplication.getStatus() != ApplicationStatus.APPROVED) {
            throw new UniqueConstraintException(Constants.SUBMISSION_ERROR_MSG);
        }

        thesis.setThesisApplication(thesisApplication);
        thesis.setThesisStatus(ThesisStatus.TO_BE_REVIEWED);
        thesisRepository.save(thesis);
    }
}
