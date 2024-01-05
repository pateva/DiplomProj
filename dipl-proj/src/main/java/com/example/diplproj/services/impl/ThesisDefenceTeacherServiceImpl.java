package com.example.diplproj.services.impl;

import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.models.associations.keys.DefenceTeacherKey;
import com.example.diplproj.data.repositories.associations.ThesisDefenceTeacherRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.services.contracts.ThesisDefenceTeacherService;
import com.example.diplproj.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisDefenceTeacherServiceImpl implements ThesisDefenceTeacherService {
    private final ThesisDefenceTeacherRepository thesisDefenceTeacherRepository;

    @Override
    public void saveAll(Iterable<ThesisDefenseTeacher> thesisDefenseTeachers) {
        thesisDefenceTeacherRepository.saveAll(thesisDefenseTeachers);
    }

    @Override
    public boolean existsById(Long teacherId, Long defenceId) {
        return thesisDefenceTeacherRepository.existsById(DefenceTeacherKey
                .builder()
                .defenceId(defenceId)
                .teacherId(teacherId).build());
    }

    @Override
    public void deleteThesisDefenceTeacher(Long teacherId, Long defenceId) {
        DefenceTeacherKey defenceTeacherKey = DefenceTeacherKey
                .builder()
                .teacherId(teacherId)
                .defenceId(defenceId)
                .build();

        if(!thesisDefenceTeacherRepository.existsById(defenceTeacherKey)) {
            throw new EntityDoesNotExistException(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG);
        }

        thesisDefenceTeacherRepository.deleteById(defenceTeacherKey);
    }
}
