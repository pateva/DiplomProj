package com.example.diplproj.data.models;

import com.example.diplproj.utils.converters.JobTitleConverter;
import com.example.diplproj.utils.enums.JobTitle;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Teacher extends User {
    @Column(name = "job_title")
    @Convert(converter = JobTitleConverter.class)
    private JobTitle jobTitle;

    @OneToMany(mappedBy = "teacher")
    private Set<ThesisApplication> thesisApplications;

    @OneToMany(mappedBy = "teacher")
    private Set<ThesisReview> thesisReviews;

    @OneToMany(mappedBy = "teacher")
    private Set<Thesis> theses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;

        return jobTitle == teacher.jobTitle;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), jobTitle);
    }
}
