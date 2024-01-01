package com.example.diplproj.data.models;

import com.example.diplproj.utils.converters.ThesisStatusConverter;
import com.example.diplproj.utils.enums.ThesisStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "thesis")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Thesis {
    @Id
    @Column(name = "thesis_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long thesisId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "createdAt")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "thesis_status")
    @Convert(converter = ThesisStatusConverter.class)
    private ThesisStatus thesisStatus;

    @Column(name = "grade")
    private Integer grade;

    @OneToMany(mappedBy = "thesis")
    private Set<ThesisReview> thesisReviews;
}
