package com.example.diplproj.data.dtos;


import com.example.diplproj.utils.enums.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@SuperBuilder
public class TeacherDto extends UserDto {
    private JobTitle jobTitle;
    private Set<ThesisApplicationDto> thesisApplicationDtos;
    private Set<ThesisReviewDto> thesisReviewDtos;
}
