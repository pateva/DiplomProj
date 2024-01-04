package com.example.diplproj.services.impl;

import com.example.diplproj.data.models.associations.ThesisDefenseStudent;
import com.example.diplproj.data.repositories.associations.ThesisDefenseStudentRepository;
import com.example.diplproj.services.contracts.ThesisDefenseStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisDefenseStudentServiceImpl implements ThesisDefenseStudentService {
    private final ThesisDefenseStudentRepository thesisDefenseStudentRepository;

    @Override
    public void saveAll(Iterable<ThesisDefenseStudent> thesisDefenseStudents) {
        thesisDefenseStudentRepository.saveAll(thesisDefenseStudents);
    }
}
