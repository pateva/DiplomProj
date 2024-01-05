package com.example.diplproj.data.models;

import com.example.diplproj.data.models.associations.ThesisDefenceStudent;
import com.example.diplproj.data.models.associations.ThesisDefenseTeacher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "thesis_defences")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThesisDefence {
    @Id
    @Column(name = "defence_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long defenceId;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "defence")
    private Set<ThesisDefenseTeacher> thesisDefenseTeachers;

    @OneToMany(mappedBy = "defence")
    private Set<ThesisDefenceStudent> thesisDefenceStudents;
}
