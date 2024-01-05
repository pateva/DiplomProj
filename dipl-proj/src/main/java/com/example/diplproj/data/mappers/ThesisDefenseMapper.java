package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisDefenseCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenseDto;
import com.example.diplproj.data.dtos.ThesisDefensePartialDto;
import com.example.diplproj.data.models.ThesisDefense;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ThesisDefenseMapper {
    ThesisDefense toThesisDefense(ThesisDefenseDto thesisDefenseDto);
    ThesisDefense toThesisDefenseFromCreation(ThesisDefenseCreationDto thesisDefenseCreationDto);
    ThesisDefenseDto toThesisDefenseDto(ThesisDefense thesisDefense);
    ThesisDefensePartialDto toThesisDefensePartialDto(ThesisDefense thesisDefense);

}
