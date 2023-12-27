package com.example.diplproj.models.associations;

import com.example.diplproj.models.Teacher;
import com.example.diplproj.models.ThesisDefense;
import com.example.diplproj.models.associations.keys.DefenseTeacherKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "thesis_defense_teacher")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThesisDefenseTeacher {
    @Id
    private DefenseTeacherKey defenseTeacherKey;

    @ManyToOne
    @MapsId("defenseId")
    @JoinColumn(name = "defense_id")
    private ThesisDefense defense;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "datetime")
    private LocalDateTime dateTime;
}
