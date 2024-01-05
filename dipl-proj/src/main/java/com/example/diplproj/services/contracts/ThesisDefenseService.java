package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.dtos.ThesisDefencePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenceUsersDto;
import com.example.diplproj.data.models.ThesisDefense;
import org.springframework.data.domain.Page;

public interface ThesisDefenseService {
    ThesisDefenceDto createThesisDefence(ThesisDefenceCreationDto thesisDefenseDto);

    ThesisDefense getThesisDefenceById(Long id);

    ThesisDefenceDto getThesisDefenceDto(Long id);

    ThesisDefenceDto addUsersToThesisDefence(Long id, ThesisDefenceUsersDto thesisDefenceUsersDto);

    Page<ThesisDefencePartialDto> getThesisDefences(int page, int size);

    ThesisDefenceDto updateThesisDefence(Long id, ThesisDefenceCreationDto thesisCreationDto);

    ThesisDefenceDto removeStudentFromDefence(Long id, Long studentId);
}
