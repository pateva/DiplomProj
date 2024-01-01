package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.ThesisApplicationPartialDto;
import org.springframework.data.domain.Page;

public interface ThesisApplicationService {
Page<ThesisApplicationPartialDto> getThesisApplications(int page, int size);
}
