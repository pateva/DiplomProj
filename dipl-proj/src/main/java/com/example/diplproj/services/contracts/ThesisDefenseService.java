package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisDefenseCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenseDto;
import com.example.diplproj.data.models.ThesisDefense;

public interface ThesisDefenseService {
    ThesisDefenseDto createThesisDefense(ThesisDefenseCreationDto thesisDefenseDto);

    ThesisDefense getThesisDefenseById(Long id);
}
