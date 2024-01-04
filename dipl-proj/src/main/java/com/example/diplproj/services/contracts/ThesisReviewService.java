package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisReviewCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewDto;

public interface ThesisReviewService {
    ThesisReviewDto createThesisReview(ThesisReviewCreationDto thesisReviewCreationDto, String email);
    ThesisReviewDto getThesisReviewDtoById(Long id);
}
