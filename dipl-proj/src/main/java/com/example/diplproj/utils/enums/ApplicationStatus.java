package com.example.diplproj.utils.enums;

import com.example.diplproj.exceptions.EnumValueNotAllowedException;
import com.example.diplproj.utils.Constants;
import lombok.Getter;

@Getter
public enum ApplicationStatus implements IntegerValueEnum<ApplicationStatus> {
    TO_BE_REVIEWED(1),
    APPROVED(2),
    NOT_APPROVED(3);

    private final int value;

    ApplicationStatus(int val) {
        this.value = val;
    }

    @Override
    public ApplicationStatus fromValue(int value) {
        for(ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if(applicationStatus.getValue() == value) {
                return applicationStatus;
            }
        }

        throw new EnumValueNotAllowedException(String.format(Constants.ENUM_VALUE_NOT_ALLOWED_ERROR_MSG, "application status"));
    }

    public static ApplicationStatus getFromValue(int value) {
        for(ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if(applicationStatus.getValue() == value) {
                return applicationStatus;
            }
        }

        throw new EnumValueNotAllowedException(String.format(Constants.ENUM_VALUE_NOT_ALLOWED_ERROR_MSG, "application status"));
    }
}
