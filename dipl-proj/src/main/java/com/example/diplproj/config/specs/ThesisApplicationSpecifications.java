package com.example.diplproj.config.specs;

import com.example.diplproj.data.models.ThesisApplication;
import com.example.diplproj.utils.enums.ApplicationStatus;
import org.springframework.data.jpa.domain.Specification;

public class ThesisApplicationSpecifications {
    public static Specification<ThesisApplication> titleContains(String title) {
        return (root, query, cb) -> title == null ? cb.isTrue(cb.literal(true)) :
                cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<ThesisApplication> hasStatus(ApplicationStatus status) {
        return (root, query, cb) -> status == null ? cb.isTrue(cb.literal(true)) :
                cb.equal(root.get("status"), status);
    }

    public static Specification<ThesisApplication> hasTeacher(Long teacherId) {
        return (root, query, cb) -> teacherId == null ? cb.isTrue(cb.literal(true)) :
                cb.equal(root.get("teacher").get("id"), teacherId);
    }
}
