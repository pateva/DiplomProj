package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisReviewDto;
import com.example.diplproj.data.models.ThesisReview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThesisReviewMapper {
    ThesisReview toThesisReview(ThesisReviewDto thesisReviewDto);
    ThesisReviewDto toThesisReviewDto(ThesisReview thesisReview);
}
