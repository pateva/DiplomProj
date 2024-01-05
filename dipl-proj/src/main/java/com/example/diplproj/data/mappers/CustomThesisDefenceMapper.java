package com.example.diplproj.data.mappers;


import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.models.ThesisDefence;

public interface CustomThesisDefenceMapper {
    ThesisDefenceDto toThesisDefenceDto(ThesisDefence thesisDefence);
}
