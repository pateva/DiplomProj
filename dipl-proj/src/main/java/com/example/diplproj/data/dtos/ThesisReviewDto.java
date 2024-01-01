package com.example.diplproj.data.dtos;

import com.example.diplproj.data.models.Thesis;

import java.time.LocalDateTime;

public class ThesisReviewDto {
    private Long reviewId;
    private Thesis thesis;
    private TeacherDto teacher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String review;
    private boolean conclusion;
}
