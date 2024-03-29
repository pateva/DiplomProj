package com.example.diplproj.data.dtos;

import com.example.diplproj.data.models.Thesis;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThesisReviewDto {
    @JsonProperty("review_id")
    private Long reviewId;
    @JsonProperty("thesisTitleId")
    private Map<Long, String> thesisIdTitle;
    @JsonProperty("teacher")
    private Map<Long, List<String>> teacherIdName;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("review")
    private String review;
    @JsonProperty("conclusion")
    private boolean conclusion;
}
