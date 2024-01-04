package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisApplicationCreationDto;
import com.example.diplproj.data.dtos.ThesisApplicationDto;
import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.mappers.ThesisApplicationMapper;
import com.example.diplproj.data.models.ThesisApplication;
import com.example.diplproj.data.repositories.ThesisApplicationRepository;
import com.example.diplproj.exceptions.EnumValueNotAllowedException;
import com.example.diplproj.services.contracts.DepartmentService;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.services.contracts.ThesisApplicationService;
import com.example.diplproj.utils.Constants;
import com.example.diplproj.utils.enums.ApplicationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {
    private final ThesisApplicationRepository thesisApplicationRepository;
    private final ThesisApplicationMapper thesisApplicationMapper;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final DepartmentService departmentService;

    @Override
    public Page<ThesisApplicationPartialDto> getThesisApplications(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ThesisApplication> thesisApplicationPage = thesisApplicationRepository.findAll(pageable);

        return thesisApplicationPage.map(thesisApplicationMapper::thesisApplicationToThesisApplicationPartialDto);
    }

    @Override
    public Page<ThesisApplicationPartialDto> getThesisApplicationsByStatus(int page, int size, int status) {
        Pageable pageable = PageRequest.of(page, size);
        ApplicationStatus applicationStatus = ApplicationStatus.getFromValue(status);
        Page<ThesisApplication> thesisApplicationPage = thesisApplicationRepository.findAllByStatus(pageable, applicationStatus);

        return thesisApplicationPage.map(thesisApplicationMapper::thesisApplicationToThesisApplicationPartialDto);
    }

    @Override
    public ThesisApplication createThesisApplication(ThesisApplicationCreationDto thesisApplicationCreationDto, String teacherEmail) {
        ThesisApplication thesisApplication = thesisApplicationMapper.toThesisApplication(thesisApplicationCreationDto);
        thesisApplication.setStudent(studentService.getStudentById(thesisApplicationCreationDto.getStudentId()))
                .setTeacher(teacherService.getTeacherByEmail(teacherEmail))
                .setDepartment(departmentService.getById(thesisApplicationCreationDto.getDepartmentId()))
                .setStatus(ApplicationStatus.TO_BE_REVIEWED);

        return thesisApplicationRepository.save(thesisApplication);
    }

    @Override
    public void updateThesisApplicationStatus(Long id, int status) {
        ThesisApplication thesisApplication = getThesisApplicationById(id);
        thesisApplication.setStatus(ApplicationStatus.getFromValue(status));

        thesisApplicationRepository.save(thesisApplication);
    }

    @Override
    public ThesisApplicationDto getThesisApplicationDtoById(Long id) {
        return thesisApplicationMapper.toThesisApplicationDto(getThesisApplicationById(id));
    }

    private ThesisApplication getThesisApplicationById(Long id) {
        Optional<ThesisApplication> thesisApplicationOpt = thesisApplicationRepository.findById(id);

        if (thesisApplicationOpt.isEmpty()) {
            throw new EnumValueNotAllowedException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Application", "id"));
        }

        return thesisApplicationOpt.get();
    }
}
