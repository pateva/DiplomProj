package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisDefenceCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenceDto;
import com.example.diplproj.data.dtos.ThesisDefencePartialDto;
import com.example.diplproj.data.dtos.ThesisDefenceUsersDto;
import com.example.diplproj.data.mappers.ThesisDefenceMapper;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.ThesisDefence;
import com.example.diplproj.data.models.associations.ThesisDefenceStudent;
import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenceStudentKey;
import com.example.diplproj.data.models.associations.keys.DefenceTeacherKey;
import com.example.diplproj.data.repositories.ThesisDefenceRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.services.contracts.StudentService;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.services.contracts.ThesisDefenceService;
import com.example.diplproj.services.contracts.ThesisDefenceStudentService;
import com.example.diplproj.services.contracts.ThesisDefenceTeacherService;
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
public class ThesisDefenceServiceImpl implements ThesisDefenceService {
    private final ThesisDefenceMapper thesisDefenceMapper;
    private final ThesisDefenceRepository thesisDefenceRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ThesisDefenceStudentService thesisDefenceStudentService;
    private final ThesisDefenceTeacherService thesisDefenceTeacherService;

    @Override
    public ThesisDefenceDto createThesisDefence(ThesisDefenceCreationDto thesisDefenseDto) {
        ThesisDefence thesisDefence = thesisDefenceMapper.toThesisDefenseFromCreation(thesisDefenseDto);
        thesisDefence = thesisDefenceRepository.save(thesisDefence);

        return thesisDefenceMapper.toThesisDefenseDto(getThesisDefenceById(thesisDefence.getDefenceId()));
    }

    @Override
    public ThesisDefence getThesisDefenceById(Long id) {
        Optional<ThesisDefence> thesisDefenseOpt = thesisDefenceRepository.findById(id);

        if (thesisDefenseOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Defense", "this is"));
        }

        return thesisDefenseOpt.get();
    }

    @Override
    public ThesisDefenceDto getThesisDefenceDto(Long id) {

        return thesisDefenceMapper.toThesisDefenseDto(getThesisDefenceById(id));
    }

    @Override
    public ThesisDefenceDto addUsersToThesisDefence(Long id, ThesisDefenceUsersDto thesisDefenceUsersDto) {
        ThesisDefence thesisDefence = getThesisDefenceById(id);
        List<Long> users = thesisDefenceUsersDto.getTeachers();
        List<ThesisDefenseTeacher> thesisDefenseTeacherList = new ArrayList<>();
        List<ThesisDefenceStudent> thesisDefenceStudentsList = new ArrayList<>();

        if (!users.isEmpty()) {
            for (Long teacherId : users) {
                Teacher teacher = teacherService.getTeacherById(teacherId);
                ThesisDefenseTeacher thesisDefenseTeacher = ThesisDefenseTeacher
                        .builder()
                        .teacher(teacher)
                        .defence(thesisDefence)
                        .defenceTeacherKey(DefenceTeacherKey
                                .builder()
                                .defenceId(id)
                                .teacherId(teacherId)
                                .build())
                        .dateTime(thesisDefence.getDateTime())
                        .build();
                thesisDefenseTeacherList.add(thesisDefenseTeacher);
            }
        }

        users = thesisDefenceUsersDto.getStudents();

        if (!users.isEmpty()) {
            for (Long studentId : users) {
                Student student = studentService.getStudentById(studentId);
                ThesisDefenceStudent thesisDefenceStudent = ThesisDefenceStudent
                        .builder()
                        .defence(thesisDefence)
                        .student(student)
                        .thesisDefenceStudentKey(DefenceStudentKey
                                .builder()
                                .defenceId(id)
                                .studentId(studentId)
                                .build())
                        .build();
                thesisDefenceStudentsList.add(thesisDefenceStudent);
            }
        }

        thesisDefenceTeacherService.saveAll(thesisDefenseTeacherList);
        thesisDefenceStudentService.saveAll(thesisDefenceStudentsList);

        return thesisDefenceMapper.toThesisDefenseDto(getThesisDefenceById(id));
    }

    @Override
    public Page<ThesisDefencePartialDto> getThesisDefences(int page, int size) {
        Page<ThesisDefence> thesisDefensePage = thesisDefenceRepository.findAll(PageRequest.of(page, size));

        return thesisDefensePage.map(thesisDefenceMapper::toThesisDefensePartialDto);
    }

    @Override
    public ThesisDefenceDto updateThesisDefence(Long id, ThesisDefenceCreationDto thesisDefenceCreationDto) {
        ThesisDefence thesisDefence = getThesisDefenceById(id);
        thesisDefence.setDateTime(thesisDefenceCreationDto.getDateTime());
        thesisDefenceRepository.save(thesisDefence);

        return getThesisDefenceDto(id);
    }

    @Override
    public ThesisDefenceDto removeStudentFromDefence(Long id, Long studentId) {
        if (!thesisDefenceRepository.existsById(id)) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Defense", "this Id"));
        } else if (!studentService.existsById(studentId)) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Student", "this id"));
        } else if (!thesisDefenceStudentService.existsById(studentId, id)) {
            throw new EntityDoesNotExistException(Constants.USER_NOT_REGISTERED_ERROR_MSG);
        }

        thesisDefenceStudentService.deleteThesisDefenceStudent(studentId, id);

        return getThesisDefenceDto(id);
    }

    @Override
    public ThesisDefenceDto removeTeacherFromDefence(Long id, Long teacherId) {
        if (!thesisDefenceRepository.existsById(id)) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Defense", "this Id"));
        } else if (!teacherService.existsById(teacherId)) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Teacher", "this id"));
        } else if (!thesisDefenceTeacherService.existsById(teacherId, id)) {
            throw new EntityDoesNotExistException(Constants.USER_NOT_REGISTERED_ERROR_MSG);
        }

        thesisDefenceTeacherService.deleteThesisDefenceTeacher(teacherId, id);

        return getThesisDefenceDto(id);
    }

    @Override
    public void deleteThesisDefence(Long id) {
        if (!thesisDefenceRepository.existsById(id)) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Defense", "this Id"));
        }

        thesisDefenceRepository.deleteById(id);
    }
}
