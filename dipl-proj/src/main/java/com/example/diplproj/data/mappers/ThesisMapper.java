package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.dtos.ThesisDto;
import com.example.diplproj.data.models.Thesis;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ThesisReviewMapper.class})
public interface ThesisMapper {
    Thesis toThesis(ThesisDto thesisDto);
    ThesisDto toThesisDto(Thesis thesis);
    Thesis toThesisFromCreationDto(ThesisCreationDto thesisCreationDto);
}
