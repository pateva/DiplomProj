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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThesisReviewCreationDto {
    @JsonProperty("thesis_id")
    private Long thesisId;
    @JsonProperty("review")
    private String review;
    @JsonProperty("conclusion")
    private boolean conclusion;
}
