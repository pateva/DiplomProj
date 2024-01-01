package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.models.ThesisApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThesisApplicationMapper {
    ThesisApplicationPartialDto thesisApplicationToThesisApplicationPartialDto(ThesisApplication application);

}
