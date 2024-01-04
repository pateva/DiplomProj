package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisApplicationCreationDto;
import com.example.diplproj.data.dtos.ThesisApplicationDto;
import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.models.ThesisApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThesisApplicationMapper {
    @Mapping(target = "departmentName", source = "department.name")
    ThesisApplicationPartialDto thesisApplicationToThesisApplicationPartialDto(ThesisApplication application);
    ThesisApplication toThesisApplication(ThesisApplicationCreationDto thesisApplicationCreationDto);
    @Mapping(target = "departmentName", source = "department.name")
    ThesisApplicationDto toThesisApplicationDto(ThesisApplication thesisApplication);

}
