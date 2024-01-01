package com.example.diplproj.data.dtos;

import com.example.diplproj.data.models.Department;
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
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Department department;
    private StudentDto student;
    private TeacherDto teacher;
}
