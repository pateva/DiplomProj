package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.dtos.ThesisDto;
import com.example.diplproj.data.dtos.ThesisPartialDto;
import com.example.diplproj.data.models.Thesis;
import com.example.diplproj.data.models.ThesisApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ThesisReviewMapper.class, TeacherMapper.class})
public interface ThesisMapper {
    Thesis toThesis(ThesisDto thesisDto);

    ThesisPartialDto toThesisPartialDto(Thesis thesis);

    @Mapping(target = "applicationId", source = "thesis.thesisApplication.applicationId")
    ThesisDto toThesisDto(Thesis thesis);

    Thesis toThesisFromCreationDto(ThesisCreationDto thesisCreationDto);

}
