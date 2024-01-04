package com.example.diplproj.data.repositories;

import com.example.diplproj.data.models.ThesisDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisDefenseRepository extends JpaRepository<ThesisDefense, Long> {

}
