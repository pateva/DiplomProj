package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisDefenseCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenseDto;
import com.example.diplproj.data.dtos.ThesisDefensePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenseUsersDto;
import com.example.diplproj.data.models.ThesisDefense;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ThesisDefenseService {
    ThesisDefenseDto createThesisDefense(ThesisDefenseCreationDto thesisDefenseDto);

    ThesisDefense getThesisDefenseById(Long id);

    ThesisDefenseDto getThesisDefenseDto(Long id);

    ThesisDefenseDto addUsersToThesisDefense(Long id, ThesisDefenseUsersDto thesisDefenseUsersDto);

    Page<ThesisDefensePartialDto> getThesisDefenses(int page, int size);
}
