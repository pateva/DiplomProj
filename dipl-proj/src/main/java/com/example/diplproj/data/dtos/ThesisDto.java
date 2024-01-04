package com.example.diplproj.data.dtos;

import com.example.diplproj.utils.enums.ThesisStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("thesis_id")
    private Long thesisId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("text")
    private String text;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("thesis_status")
    private ThesisStatus thesisStatus;
    @JsonProperty("grade")
    private Integer grade;
    @JsonProperty("thesis_reviews")
    private Set<ThesisReviewDto> thesisReviews;
    @JsonProperty("teacher")
    private TeacherDto teacher;
    @JsonProperty("thesis_application_id")
    private Long applicationId;
}
