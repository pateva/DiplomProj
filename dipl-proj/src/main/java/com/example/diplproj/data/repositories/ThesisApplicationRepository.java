package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.ThesisApplication;
import com.example.diplproj.utils.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long> {
    Page<ThesisApplication> findAll(Pageable pageable);
    Page<ThesisApplication> findAllByStatus(Pageable pageable, ApplicationStatus applicationStatus);
    Page<ThesisApplication> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
