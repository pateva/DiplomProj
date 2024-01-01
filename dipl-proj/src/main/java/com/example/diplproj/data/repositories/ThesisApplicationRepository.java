package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.ThesisApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long> {
    Page<ThesisApplication> findAll(Pageable pageable);
}
