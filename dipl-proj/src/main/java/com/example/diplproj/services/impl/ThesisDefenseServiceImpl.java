package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisDefenseCreationDto;
import com.example.diplproj.data.dtos.ThesisDefenseDto;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ThesisDefenseServiceImpl implements ThesisDefenseService {
    private final ThesisDefenseMapper thesisDefenseMapper;
    private final ThesisDefenseRepository thesisDefenseRepository;

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
}
