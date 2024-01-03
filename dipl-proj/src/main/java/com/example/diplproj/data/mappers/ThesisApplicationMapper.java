package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisApplicationCreationDto;
import com.example.diplproj.data.dtos.ThesisApplicationDto;
import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.models.ThesisApplication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThesisApplicationMapper {
    ThesisApplicationPartialDto thesisApplicationToThesisApplicationPartialDto(ThesisApplication application);
    ThesisApplication toThesisApplication(ThesisApplicationCreationDto thesisApplicationCreationDto);
    ThesisApplicationDto toThesisApplicationDto(ThesisApplication thesisApplication);

}
