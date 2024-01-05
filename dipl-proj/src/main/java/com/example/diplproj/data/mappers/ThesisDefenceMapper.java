package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.dtos.ThesisDefencePartialDto;
import com.example.diplproj.data.models.ThesisDefence;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ThesisDefenceMapper {
    ThesisDefence toThesisDefense(ThesisDefenceDto thesisDefenceDto);
    ThesisDefence toThesisDefenseFromCreation(ThesisDefenceCreationDto thesisDefenceCreationDto);
    ThesisDefenceDto toThesisDefenseDto(ThesisDefence thesisDefence);
    ThesisDefencePartialDto toThesisDefensePartialDto(ThesisDefence thesisDefence);

}
