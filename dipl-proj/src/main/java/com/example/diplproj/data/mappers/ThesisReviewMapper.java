package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisReviewCreationDto;
import com.example.diplproj.data.dtos.ThesisReviewDto;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.Thesis;
import com.example.diplproj.data.models.ThesisReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ThesisReviewMapper {
    ThesisReview toThesisReview(ThesisReviewDto thesisReviewDto);
    @Mapping(target = "thesisIdTitle", expression = "java(mapThesisToIdNameMap(thesisReview.getThesis()))")
    @Mapping(target = "teacherIdName", expression = "java(mapTeacherToIdNames(thesisReview.getTeacher()))")
    ThesisReviewDto toThesisReviewDto(ThesisReview thesisReview);
    ThesisReview toThesisReviewFromCreating(ThesisReviewCreationDto thesisReviewCreationDto);

    default Map<Long, String> mapThesisToIdNameMap(Thesis thesis) {
        Map<Long, String> map = new HashMap<>();
        if (thesis != null) {
            map.put(thesis.getThesisId(), thesis.getTitle());
        }
        return map;
    }

    default Map<Long, List<String>> mapTeacherToIdNames(Teacher teacher) {
        Map<Long, List<String>> map = new HashMap<>();
        if (teacher != null) {
            map.put(teacher.getUserId(), List.of(teacher.getFirstName(), teacher.getLastName()));
        }

        return map;
    }
}
