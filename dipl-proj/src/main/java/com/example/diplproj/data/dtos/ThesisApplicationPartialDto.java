package com.example.diplproj.data.dtos;

import com.example.diplproj.data.models.Department;
import com.example.diplproj.utils.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThesisApplicationPartialDto {
    @JsonProperty("thesis_application_id")
    private Long applicationId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("department_name")
    private String departmentName;
    @JsonProperty("student")
    private StudentPartialDto student;
    @JsonProperty("teacher")
    private TeacherPartialDto teacher;
    @JsonProperty("status")
    private ApplicationStatus status;
}
