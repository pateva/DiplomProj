package com.example.diplproj.data.dtos;

import com.example.diplproj.utils.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private StudentDto student;
    private TeacherDto teacher;
}
