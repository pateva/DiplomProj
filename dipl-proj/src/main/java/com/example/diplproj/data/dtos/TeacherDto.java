package com.example.diplproj.data.dtos;


import com.example.diplproj.utils.enums.JobTitle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
    @JsonProperty("job_title")
    private JobTitle jobTitle;
    @JsonProperty("thesis_applications")
    private Set<ThesisApplicationDto> thesisApplicationDtos;
    @JsonProperty("thesis_reviews")
    private Set<ThesisReviewDto> thesisReviewDtos;
}
