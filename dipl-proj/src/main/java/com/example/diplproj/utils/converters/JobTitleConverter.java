package com.example.diplproj.utils.converters;

import com.example.diplproj.utils.enums.JobTitle;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JobTitleConverter extends IntegerValueEnumConverter<JobTitle> {
    public JobTitleConverter() {
        super(JobTitle.class);
    }
}
