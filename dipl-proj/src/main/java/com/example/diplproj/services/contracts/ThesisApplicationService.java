package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisApplicationCreationDto;
import com.example.diplproj.data.dtos.ThesisApplicationDto;
import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.models.ThesisApplication;
import com.example.diplproj.utils.enums.ApplicationStatus;
import org.springframework.data.domain.Page;

public interface ThesisApplicationService {
    Page<ThesisApplicationPartialDto> getThesisApplications(int page, int size);
    Page<ThesisApplicationPartialDto> getThesisApplicationsByStatus(int page, int size, int status);
    ThesisApplication createThesisApplication(ThesisApplicationCreationDto thesisApplicationCreationDto, String teacherEmail);
    void updateThesisApplicationStatus(Long id, int status);
    ThesisApplicationDto getThesisApplicationDtoById(Long id);
}
