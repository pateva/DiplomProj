package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.ThesisDefence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisDefenceRepository extends JpaRepository<ThesisDefence, Long> {
    Page<ThesisDefence> findAll(Pageable pageable);
}
