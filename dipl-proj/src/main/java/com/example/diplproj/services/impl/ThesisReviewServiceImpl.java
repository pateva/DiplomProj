package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisReviewCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewDto;
import com.example.diplproj.data.mappers.ThesisReviewMapper;
import com.example.diplproj.data.models.Student;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Page<ThesisReviewDto> getThesisReviewsByThesis(Long id, int page, int size) {
        Thesis thesis = thesisService.getThesisById(id);
        Page<ThesisReview> thesisPage = thesisReviewRepository.findAllByThesis(thesis, PageRequest.of(page, size));

        return thesisPage.map(thesisReviewMapper::toThesisReviewDto);
    }

    @Override
    public int getByReviewsStudentCount(boolean conclusion) {
        List<ThesisReview> thesisReviewList = thesisReviewRepository.findAllByConclusion(conclusion);
        Set<Student> studentSet = thesisReviewList.stream().map(ThesisReview::getThesis).map(Thesis::getStudent).collect(Collectors.toSet());

        return studentSet.size();
    }

}
