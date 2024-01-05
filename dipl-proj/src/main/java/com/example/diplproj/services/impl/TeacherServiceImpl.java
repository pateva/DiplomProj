package com.example.diplproj.services.impl;

import com.example.diplproj.clients.Auth0Service;
import com.example.diplproj.data.dtos.TeacherDto;
import com.example.diplproj.data.mappers.TeacherMapper;
import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.repositories.TeacherRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.exceptions.UniqueConstraintException;
import com.example.diplproj.services.contracts.TeacherService;
import com.example.diplproj.services.contracts.ThesisDefenceTeacherService;
import com.example.diplproj.utils.Constants;
import com.example.diplproj.utils.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final Auth0Service auth0Service;
    private final ThesisDefenceTeacherService thesisDefenceTeacherService;

    @Override
    public Page<TeacherDto> getAllTeachers(int page, int size) {
        Page<Teacher> teacherPage = teacherRepository.findAll(PageRequest.of(page, size));

        return teacherPage.map(teacherMapper::toTeacherDto);
    }

    @Override
    public void createTeacher(TeacherDto teacherDto) {
        if (teacherDto.getEmail() == null || teacherRepository.existsByEmail(teacherDto.getEmail())) {
            throw new UniqueConstraintException(String.format(Constants.UNIQUE_CONSTRAINT_ERROR_MSG, "email"));
        }

        auth0Service.createUser(teacherDto.getEmail(), teacherDto.getLastName().concat("A123!").toCharArray(), List.of(Roles.TEACHER.getVal()));
        teacherRepository.save(teacherMapper.toTeacher(teacherDto));
    }

    @Override
    public TeacherDto getTeacherDtoByEmail(String email) {

        return teacherMapper.toTeacherDto(getTeacherByEmail(email));
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        Optional<Teacher> teacherOpt = teacherRepository.findByEmail(email);

        if (teacherOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Teacher", "email address"));
        }

        return teacherOpt.get();
    }

    @Override
    public void updateTeacher(Long id, TeacherDto teacherDto) {
        Teacher teacher = getTeacherById(id);

        teacher.setFirstName(teacherDto.getFirstName())
                .setLastName(teacherDto.getLastName());
        teacher.setJobTitle(teacherDto.getJobTitle());

        if (!teacher.getEmail().equals(teacherDto.getEmail())) {
            if (teacherDto.getEmail() == null || teacherRepository.existsByEmail(teacherDto.getEmail())) {
                throw new UniqueConstraintException(Constants.INVALID_EMAIL_ERROR_MSG);
            }

            auth0Service.updateUserEmail(teacher.getEmail(), teacherDto.getEmail());
            teacher.setEmail(teacherDto.getEmail());
        }

        teacherRepository.save(teacher);
    }

    @Override
    public TeacherDto getById(Long id) {
        return teacherMapper.toTeacherDto(getTeacherById(id));
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.delete(getTeacherById(id));
    }

    @Override
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public List<Teacher> getTeachersToDefence(Long defenceId) {
        List<ThesisDefenseTeacher> thesisDefenseTeachers = thesisDefenceTeacherService.getThesisDefenceTeachersByDefenceId(defenceId);

        return thesisDefenseTeachers
                .stream()
                .map(ThesisDefenseTeacher::getTeacher).toList();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);

        if (teacherOpt.isEmpty()) {
            throw new EntityDoesNotExistException(String.format(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG, "Teacher", "this id"));
        }

        return teacherOpt.get();
    }
}

