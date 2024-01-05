package com.example.diplproj.services.contracts;

import com.example.diplproj.data.dtos.StudentDto;
import com.example.diplproj.data.dtos.ThesisCreationDto;
import com.example.diplproj.data.dtos.ThesisDto;
import com.example.diplproj.data.dtos.ThesisPartialDto;
import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.models.Thesis;
import com.example.diplproj.utils.enums.ThesisStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ThesisService {
    void createThesis(ThesisCreationDto thesisCreationDto, String studentEmail);
    ThesisDto getThesisById(Long id, String role, String userEmail);
    Thesis getThesisById(Long id);
    Page<ThesisPartialDto> findByUserAndStatus(String role, String email,Integer status, int page, int size);
    ThesisDto updateThesisStatus(Long id, Integer status, Integer grade);
    List<StudentDto> getAllByGrade(int a, int b);
}
