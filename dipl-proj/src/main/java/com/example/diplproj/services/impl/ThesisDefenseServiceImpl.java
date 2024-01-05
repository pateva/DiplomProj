package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.dtos.ThesisDefencePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenceUsersDto;
import com.example.diplproj.data.mappers.ThesisDefenseMapper;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.ThesisDefense;
import com.example.diplproj.data.models.associations.ThesisDefenseStudent;
import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenceStudentKey;
import com.example.diplproj.data.models.associations.keys.DefenseTeacherKey;
import com.example.diplproj.data.repositories.ThesisDefenseRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.services.contracts.ThesisDefenseService;
import com.example.diplproj.services.contracts.ThesisDefenseStudentService;
import com.example.diplproj.services.contracts.ThesisDefenseTeacherService;
import com.example.diplproj.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ThesisDefenseServiceImpl implements ThesisDefenseService {
    private final ThesisDefenseMapper thesisDefenseMapper;
    private final ThesisDefenseRepository thesisDefenseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ThesisDefenseStudentService thesisDefenseStudentService;
    private final ThesisDefenseTeacherService thesisDefenseTeacherService;

    @Override
    public ThesisDefenceDto createThesisDefence(ThesisDefenceCreationDto thesisDefenseDto) {
        ThesisDefense thesisDefense = thesisDefenseMapper.toThesisDefenseFromCreation(thesisDefenseDto);
        thesisDefense = thesisDefenseRepository.save(thesisDefense);

        return thesisDefenseMapper.toThesisDefenseDto(getThesisDefenceById(thesisDefense.getDefenseId()));
    }

    @Override
    public ThesisDefense getThesisDefenceById(Long id) {
        Optional<ThesisDefense> thesisDefenseOpt = thesisDefenseRepository.findById(id);

        if (thesisDefenseOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Defense", "this is"));
        }

        return thesisDefenseOpt.get();
    }

    @Override
    public ThesisDefenceDto getThesisDefenceDto(Long id) {

        return thesisDefenseMapper.toThesisDefenseDto(getThesisDefenceById(id));
    }

    @Override
    public ThesisDefenceDto addUsersToThesisDefence(Long id, ThesisDefenceUsersDto thesisDefenceUsersDto) {
        ThesisDefense thesisDefense = getThesisDefenceById(id);
        List<Long> users = thesisDefenceUsersDto.getTeachers();
        List<ThesisDefenseTeacher> thesisDefenseTeacherList = new ArrayList<>();
        List<ThesisDefenseStudent> thesisDefenseStudentsList = new ArrayList<>();

        if (!users.isEmpty()) {
            for (Long teacherId : users) {
                Teacher teacher = teacherService.getTeacherById(teacherId);
                ThesisDefenseTeacher thesisDefenseTeacher = ThesisDefenseTeacher
                        .builder()
                        .teacher(teacher)
                        .defense(thesisDefense)
                        .defenseTeacherKey(DefenseTeacherKey
                                .builder()
                                .defenseId(id)
                                .teacherId(teacherId)
                                .build())
                        .dateTime(thesisDefense.getDateTime())
                        .build();
                thesisDefenseTeacherList.add(thesisDefenseTeacher);
            }
        }

        users = thesisDefenceUsersDto.getStudents();

        if (!users.isEmpty()) {
            for (Long studentId : users) {
                Student student = studentService.getStudentById(studentId);
                ThesisDefenseStudent thesisDefenseStudent = ThesisDefenseStudent
                        .builder()
                        .defense(thesisDefense)
                        .student(student)
                        .thesisDefenceStudentKey(DefenceStudentKey
                                .builder()
                                .defenseId(id)
                                .studentId(studentId)
                                .build())
                        .build();
                thesisDefenseStudentsList.add(thesisDefenseStudent);
            }
        }

        thesisDefenseTeacherService.saveAll(thesisDefenseTeacherList);
        thesisDefenseStudentService.saveAll(thesisDefenseStudentsList);

        return thesisDefenseMapper.toThesisDefenseDto(getThesisDefenceById(id));
    }

    @Override
    public Page<ThesisDefencePartialDto> getThesisDefences(int page, int size) {
        Page<ThesisDefense> thesisDefensePage = thesisDefenseRepository.findAll(PageRequest.of(page, size));

        return thesisDefensePage.map(thesisDefenseMapper::toThesisDefensePartialDto);
    }

    @Override
    public ThesisDefenceDto updateThesisDefence(Long id, ThesisDefenceCreationDto thesisDefenceCreationDto) {
        ThesisDefense thesisDefense = getThesisDefenceById(id);
        thesisDefense.setDateTime(thesisDefenceCreationDto.getDateTime());
        thesisDefenseRepository.save(thesisDefense);

        return getThesisDefenceDto(id);
    }

    @Override
    public ThesisDefenceDto removeStudentFromDefence(Long id, Long studentId) {
        if (!thesisDefenseRepository.existsById(id)) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Defense", "this Id"));
        } else if (!studentService.existsById(studentId)) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Student", "this id"));
        } else if(!thesisDefenseStudentService.existsById(studentId, id)) {
            throw new EntityDoesNotExistException(Constants.USER_NOT_REGISTERED_ERROR_MSG);
        }

        thesisDefenseStudentService.deleteThesisDefenceStudent(studentId, id);

        return getThesisDefenceDto(id);
    }
}
