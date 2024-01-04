package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.TeacherDto;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.ThesisApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TeacherMapper {
    @Mapping(target = "thesisApplicationIdNames", expression = "java(mapThesisApplicationsToIdNameMap(teacher.getThesisApplications()))")
    TeacherDto toTeacherDto(Teacher teacher);
    Teacher toTeacher(TeacherDto teacherDto);

    default Set<Map<Long, String>> mapThesisApplicationsToIdNameMap(Set<ThesisApplication> thesisApplications) {
        if (thesisApplications == null) {
            return Collections.emptySet();
        }

        return thesisApplications.stream()
                .map(thesisApplication -> {
                    Map<Long, String> idNameMap = new HashMap<>();
                    idNameMap.put(thesisApplication.getApplicationId(), thesisApplication.getTitle());
                    return idNameMap;
                })
                .collect(Collectors.toSet());
    }
}
