package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.Teacher;
import com.example.diplproj.data.models.ThesisApplication;
import com.example.diplproj.utils.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long>, JpaSpecificationExecutor<ThesisApplication> {
    Page<ThesisApplication> findAll(Pageable pageable);

    Page<ThesisApplication> findAllByStatus(Pageable pageable, ApplicationStatus applicationStatus);

    Page<ThesisApplication> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<ThesisApplication> findByTeacherAndStatus(Teacher teacher, ApplicationStatus status, Pageable pageable);

}
