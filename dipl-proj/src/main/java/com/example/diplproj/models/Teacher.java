package com.example.diplproj.models;

import com.example.diplproj.utils.converters.JobTitleConverter;
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

import java.util.Set;

@Entity
@Table(name = "teachers")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher extends User {
    @Column(name = "job_title")
    @Convert(converter = JobTitleConverter.class)
    private Long jobTitle;

    @OneToMany(mappedBy = "teacher")
    private Set<ThesisApplication> thesisApplications;

    @OneToMany(mappedBy = "teacher")
    private Set<ThesisReview> thesisReviews;
}
