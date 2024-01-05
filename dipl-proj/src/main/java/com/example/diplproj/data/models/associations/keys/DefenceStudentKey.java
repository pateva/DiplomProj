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
public class DefenceStudentKey implements Serializable {
    @Column(name = "defence_id")
    private Long defenceId;

    @Column(name = "student_id")
    private Long studentId;
}
