package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.Thesis;
import com.example.diplproj.data.models.ThesisReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisReviewRepository extends JpaRepository<ThesisReview, Long> {
    Page<ThesisReview> findAllByThesis(Thesis thesis, Pageable pageable);
}
