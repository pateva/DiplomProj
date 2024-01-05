package com.example.diplproj.services.impl;

import com.example.diplproj.data.models.associations.ThesisDefenceStudent;
import com.example.diplproj.data.models.associations.keys.DefenceStudentKey;
import com.example.diplproj.data.repositories.associations.ThesisDefenceStudentRepository;
import com.example.diplproj.exceptions.EntityDoesNotExistException;
import com.example.diplproj.services.contracts.ThesisDefenceStudentService;
import com.example.diplproj.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisDefenceStudentServiceImpl implements ThesisDefenceStudentService {
    private final ThesisDefenceStudentRepository thesisDefenceStudentRepository;

    @Override
    public void saveAll(Iterable<ThesisDefenceStudent> thesisDefenseStudents) {
        thesisDefenceStudentRepository.saveAll(thesisDefenseStudents);
    }

    @Override
    public boolean existsById(Long studentId, Long defenceId) {
        return thesisDefenceStudentRepository.existsById(DefenceStudentKey
                .builder()
                .studentId(studentId)
                .defenceId(defenceId)
                .build());
    }

    @Override
    public void deleteThesisDefenceStudent(Long studentId, Long defenceId) {
        DefenceStudentKey defenceStudentKey = DefenceStudentKey
                .builder()
                .studentId(studentId)
                .defenceId(defenceId)
                .build();

        if(!thesisDefenceStudentRepository.existsById(defenceStudentKey)) {
            throw new EntityDoesNotExistException(Constants.ENTITY_DOES_NOT_EXISTS_ERROR_MSG);
        }

        thesisDefenceStudentRepository.deleteById(defenceStudentKey);
    }
}
