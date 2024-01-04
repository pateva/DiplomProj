package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisReviewCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewDto;
import com.example.diplproj.data.mappers.ThesisReviewMapper;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.Thesis;
import com.example.diplproj.data.models.ThesisReview;
import com.example.diplproj.data.repositories.ThesisReviewRepository;
import com.example.diplproj.exceptions.AuthException;
import com.example.diplproj.exceptions.UniqueConstraintException;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.services.contracts.ThesisReviewService;
import com.example.diplproj.services.contracts.ThesisService;
import com.example.diplproj.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisReviewServiceImpl implements ThesisReviewService {
    private final ThesisReviewRepository thesisReviewRepository;
    private final TeacherService teacherService;
    private final ThesisService thesisService;
    private final ThesisReviewMapper thesisReviewMapper;

    @Override
    public ThesisReviewDto createThesisReview(ThesisReviewCreationDto thesisReviewCreationDto, String email) {
        Teacher teacher = teacherService.getTeacherByEmail(email);
        Thesis thesis = thesisService.getThesisById(thesisReviewCreationDto.getThesisId());

        if (!thesis.getTeacher().equals(teacher)) {
            throw new AuthException();
        } else if (!thesisReviewCreationDto.isConclusion() || thesisReviewCreationDto.getReview().isEmpty()) {
            throw new UniqueConstraintException(Constants.MISSING_INFO_ERROR_MSG);
        }

        ThesisReview thesisReview = thesisReviewMapper.toThesisReviewFromCreating(thesisReviewCreationDto);
        thesisReview.setTeacher(teacher)
                .setThesis(thesis);

        return thesisReviewMapper.toThesisReviewDto(thesisReviewRepository.save(thesisReview));
    }

    @Override
    public ThesisReviewDto getThesisReviewDtoById(Long id) {
        return null;
    }
}
