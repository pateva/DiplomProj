package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.dtos.ThesisDto;

public interface ThesisService {
    void createThesis(ThesisCreationDto thesisCreationDto, String studentEmail);
    ThesisDto getThesisById(Long id, String role, String userEmail);
}
