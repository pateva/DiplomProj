package com.example.diplproj.utils.enums;

import com.example.diplproj.exceptions.EnumValueNotAllowedException;
import com.example.diplproj.utils.Constants;
import lombok.Getter;

@Getter
public enum JobTitle implements IntegerValueEnum<JobTitle>{
    ASSISTANT(1),
    PRINCIPAL_ASSISTANT(2),
    ASSOCIATE_PROFESSOR(3),
    PROFESSOR(4);

    private final int value;

    JobTitle(int val) {
        this.value = val;
    }

    @Override
    public JobTitle fromValue(int value) {
        for(JobTitle jobTitle : JobTitle.values()) {
            if(jobTitle.getValue() == value) {
                return jobTitle;
            }
        }

        throw new EnumValueNotAllowedException(String.format(Constants.ENUM_VALUE_NOT_ALLOWED_ERROR_MSG, "job title"));
    }
}
