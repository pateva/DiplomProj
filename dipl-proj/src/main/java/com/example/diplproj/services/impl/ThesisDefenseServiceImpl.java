package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisDefenseCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenseDto;
import com.example.diplproj.data.dtos.ThesisDefensePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenseUsersDto;
import com.example.diplproj.data.mappers.ThesisDefenseMapper;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.ThesisDefense;
import com.example.diplproj.data.models.associations.ThesisDefenseStudent;
import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenseStudentKey;
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
    public ThesisDefenseDto createThesisDefense(ThesisDefenseCreationDto thesisDefenseDto) {
        ThesisDefense thesisDefense = thesisDefenseMapper.toThesisDefenseFromCreation(thesisDefenseDto);
        thesisDefense = thesisDefenseRepository.save(thesisDefense);

        return thesisDefenseMapper.toThesisDefenseDto(getThesisDefenseById(thesisDefense.getDefenseId()));
    }

    @Override
    public ThesisDefense getThesisDefenseById(Long id) {
        Optional<ThesisDefense> thesisDefenseOpt = thesisDefenseRepository.findById(id);

        if (thesisDefenseOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Defense", "this is"));
        }

        return thesisDefenseOpt.get();
    }

    @Override
    public ThesisDefenseDto addUsersToThesisDefense(Long id, ThesisDefenseUsersDto thesisDefenseUsersDto) {
        ThesisDefense thesisDefense = getThesisDefenseById(id);
        List<Long> users = thesisDefenseUsersDto.getTeachers();
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

        users = thesisDefenseUsersDto.getStudents();

        if (!users.isEmpty()) {
            for (Long studentId : users) {
                Student student = studentService.getStudentById(studentId);
                ThesisDefenseStudent thesisDefenseStudent = ThesisDefenseStudent
                        .builder()
                        .defense(thesisDefense)
                        .student(student)
                        .thesisDefenseStudentKey(DefenseStudentKey
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

        return thesisDefenseMapper.toThesisDefenseDto(getThesisDefenseById(id));
    }

    @Override
    public Page<ThesisDefensePartialDto> getThesisDefenses() {
        return null;
    }
}
