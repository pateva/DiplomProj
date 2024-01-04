package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisReviewCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewDto;
import org.springframework.data.domain.Page;

public interface ThesisReviewService {
    ThesisReviewDto createThesisReview(ThesisReviewCreationDto thesisReviewCreationDto, String email);

    Page<ThesisReviewDto> getThesisReviewsByThesis(Long id, int page, int size);
}
