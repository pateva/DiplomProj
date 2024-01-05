package com.example.diplproj.data.models.associations;

import com.example.diplproj.data.models.Student;
import com.example.diplproj.data.models.ThesisDefence;
import com.example.diplproj.data.models.associations.keys.DefenceStudentKey;
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
@Table(name = "thesis_defence_student")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThesisDefenceStudent {
    @Id
    private DefenceStudentKey thesisDefenceStudentKey;

    @ManyToOne
    @MapsId("defenceId")
    @JoinColumn(name = "defence_id")
    private ThesisDefence defence;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;
}
