package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.dtos.ThesisDefencePartialDto;
import com.example.diplproj.data.models.ThesisDefense;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ThesisDefenseMapper {
    ThesisDefense toThesisDefense(ThesisDefenceDto thesisDefenceDto);
    ThesisDefense toThesisDefenseFromCreation(ThesisDefenceCreationDto thesisDefenceCreationDto);
    ThesisDefenceDto toThesisDefenseDto(ThesisDefense thesisDefense);
    ThesisDefencePartialDto toThesisDefensePartialDto(ThesisDefense thesisDefense);

}
