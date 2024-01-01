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
public class ThesisApplicationPartialDto {
    private Long applicationId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Department department;
    private StudentDto student;
    private TeacherDto teacher;
}
