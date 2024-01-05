package com.example.diplproj.data.dtos;

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
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThesisDefenceDto {
    @JsonProperty("defence_id")
    private Long defenceId;

    @JsonProperty("datetime")
    private LocalDateTime dateTime;

    @JsonProperty("teachers")
    private Set<Map<Long, List<String>>> teachers;

    @JsonProperty("students")
    private Set<Map<Long, List<String>>> students;
}
