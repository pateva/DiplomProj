package com.example.diplproj.utils.converters;

import com.example.diplproj.utils.enums.ApplicationStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ApplicationStatusEnumConverter extends IntegerValueEnumConverter<ApplicationStatus> {
    public ApplicationStatusEnumConverter() {
        super(ApplicationStatus.class);
    }
}
