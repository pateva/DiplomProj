package com.example.diplproj.models.associations;

import com.example.diplproj.models.Student;
import com.example.diplproj.models.ThesisDefense;
import com.example.diplproj.models.associations.keys.DefenseStudentKey;
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

@Entity
@Table(name = "thesis_defense_student")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThesisDefenseStudent {
    @Id
    private DefenseStudentKey thesisDefenseStudentKey;

    @ManyToOne
    @MapsId("defenseId")
    @JoinColumn(name = "defense_id")
    private ThesisDefense defense;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;
}
