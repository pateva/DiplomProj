package com.example.diplproj.utils.converters;

import com.example.diplproj.utils.enums.ThesisStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ThesisStatusConverter extends IntegerValueEnumConverter<ThesisStatus> {
    public ThesisStatusConverter() {
        super(ThesisStatus.class);
    }
}
