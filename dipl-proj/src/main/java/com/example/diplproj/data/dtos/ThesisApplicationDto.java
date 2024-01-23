package com.example.diplproj.data.dtos;

import com.example.diplproj.utils.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ThesisApplicationDto {
    private Long applicationId;
    private String title;
    private String purpose;
    private String tasks;
    private String techStack;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String departmentName;
    private StudentPartialDto student;
    private TeacherPartialDto teacher;
}
