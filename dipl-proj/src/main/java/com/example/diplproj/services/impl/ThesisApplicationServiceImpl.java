package com.example.diplproj.services.impl;

import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import com.example.diplproj.data.mappers.ThesisApplicationMapper;
import com.example.diplproj.data.models.ThesisApplication;
import com.example.diplproj.data.repositories.ThesisApplicationRepository;
import com.example.diplproj.services.contracts.ThesisApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {
    private final ThesisApplicationRepository thesisApplicationRepository;
    private final ThesisApplicationMapper thesisApplicationMapper;

    @Override
    public Page<ThesisApplicationPartialDto> getThesisApplications(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ThesisApplication> thesisApplicationPage = thesisApplicationRepository.findAll(pageable);

        return thesisApplicationPage.map(thesisApplicationMapper::thesisApplicationToThesisApplicationPartialDto);
    }
}
