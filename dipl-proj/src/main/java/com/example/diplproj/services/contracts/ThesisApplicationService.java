package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisApplicationCreationDto;
import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.models.ThesisApplication;
import org.springframework.data.domain.Page;

public interface ThesisApplicationService {
    Page<ThesisApplicationPartialDto> getThesisApplications(int page, int size);

    ThesisApplication createThesisApplication(ThesisApplicationCreationDto thesisApplicationCreationDto, String teacherEmail);
}
