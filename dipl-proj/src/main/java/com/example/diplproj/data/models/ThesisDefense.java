package com.example.diplproj.data.models;

import com.example.diplproj.data.models.associations.ThesisDefenseStudent;
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
@Table(name = "thesis_defenses")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThesisDefense {
    @Id
    @Column(name = "defense_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long defenseId;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "defense")
    private Set<ThesisDefenseTeacher> thesisDefenseTeachers;

    @OneToMany(mappedBy = "defense")
    private Set<ThesisDefenseStudent> thesisDefenseStudents;
}
