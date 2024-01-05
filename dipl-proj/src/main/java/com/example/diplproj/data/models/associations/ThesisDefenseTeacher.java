package com.example.diplproj.data.models.associations;

import com.example.diplproj.data.models.ThesisDefence;
import com.example.diplproj.data.models.associations.keys.DefenceTeacherKey;
import com.example.diplproj.data.models.Teacher;
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
@Table(name = "thesis_defence_teacher")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThesisDefenseTeacher {
    @Id
    private DefenceTeacherKey defenceTeacherKey;

    @ManyToOne
    @MapsId("defenceId")
    @JoinColumn(name = "defence_id")
    private ThesisDefence defence;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "datetime")
    private LocalDateTime dateTime;
}
