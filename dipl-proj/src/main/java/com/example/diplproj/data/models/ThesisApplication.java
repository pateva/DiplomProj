package com.example.diplproj.data.models;

import com.example.diplproj.utils.converters.ApplicationStatusEnumConverter;
import com.example.diplproj.utils.enums.ApplicationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Entity
@Table(name = "thesis_applications")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThesisApplication {
    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationId;

    @Column(name = "title")
    private String title;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "tasks")
    private String tasks;

    @Column(name = "technology_stack")
    private String techStack;

    @Column(name = "status")
    @Convert(converter = ApplicationStatusEnumConverter.class)
    private ApplicationStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
