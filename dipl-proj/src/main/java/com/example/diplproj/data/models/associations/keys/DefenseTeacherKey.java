package com.example.diplproj.data.models.associations.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefenseTeacherKey implements Serializable {
    @Column(name = "defense_id")
    private Long defenseId;

    @Column(name = "teacher_id")
    private Long teacherId;
}
