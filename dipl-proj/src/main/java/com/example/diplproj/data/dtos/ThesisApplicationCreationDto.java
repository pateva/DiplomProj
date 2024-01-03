package com.example.diplproj.data.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ThesisApplicationCreationDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("department_id")
    private Long departmentId;
    @JsonProperty("student_id")
    private Long studentId;
    @JsonProperty("purpose")
    private String purpose;
    @JsonProperty("tasks")
    private String tasks;
    @JsonProperty("technology_stack")
    private String techStack;
}
