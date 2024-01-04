package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisCreationDto;

public interface ThesisService {
    void createThesis(ThesisCreationDto thesisCreationDto, String studentEmail);
}
