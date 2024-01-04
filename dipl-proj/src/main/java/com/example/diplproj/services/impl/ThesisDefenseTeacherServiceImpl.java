package com.example.diplproj.services.impl;

import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import com.example.diplproj.data.repositories.associations.ThesisDefenseTeacherRepository;
import com.example.diplproj.services.contracts.ThesisDefenseTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisDefenseTeacherServiceImpl implements ThesisDefenseTeacherService {
    private final ThesisDefenseTeacherRepository thesisDefenseTeacherRepository;

    @Override
    public void saveAll(Iterable<ThesisDefenseTeacher> thesisDefenseTeachers) {
        thesisDefenseTeacherRepository.saveAll(thesisDefenseTeachers);
    }
}
