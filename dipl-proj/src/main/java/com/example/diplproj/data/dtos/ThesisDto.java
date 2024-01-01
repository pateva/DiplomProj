package com.example.diplproj.data.dtos;

import com.example.diplproj.utils.enums.ThesisStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Builder
public class ThesisDto {
    private Long thesisId;
    private String title;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ThesisStatus thesisStatus;
    private Integer grade;
    private Set<ThesisReviewDto> thesisReviews;
    private TeacherDto teacher;
}
