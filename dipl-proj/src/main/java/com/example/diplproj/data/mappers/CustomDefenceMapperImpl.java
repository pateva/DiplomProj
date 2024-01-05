package com.example.diplproj.data.mappers;

import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.models.ThesisDefence;
import com.example.diplproj.data.models.User;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.services.contracts.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomDefenceMapperImpl implements CustomThesisDefenceMapper {
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Override
    public ThesisDefenceDto toThesisDefenceDto(ThesisDefence thesisDefence) {
        ThesisDefenceDto thesisDefenceDto = ThesisDefenceDto
                .builder()
                .defenceId(thesisDefence.getDefenceId())
                .dateTime(thesisDefence.getDateTime())
                .build();

        thesisDefenceDto.setTeachers(mapUsers(teacherService.getTeachersToDefence(thesisDefenceDto.getDefenceId())));
        thesisDefenceDto.setStudents(mapUsers(studentService.getStudentToDefence(thesisDefence.getDefenceId())));

        return thesisDefenceDto;
    }

    private <T extends User> Map<Long, List<String>> mapUsers(List<T> usersList) {

        return usersList
                .stream()
                .collect(Collectors.toMap(
                        T::getUserId,
                        user -> List.of(user.getFirstName(), user.getLastName())));

    }
}
