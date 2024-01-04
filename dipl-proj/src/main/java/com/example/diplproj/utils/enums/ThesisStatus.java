package com.example.diplproj.utils.enums;

import com.example.diplproj.exceptions.EnumValueNotAllowedException;
import com.example.diplproj.utils.Constants;
import lombok.Getter;

@Getter
public enum ThesisStatus implements IntegerValueEnum<ThesisStatus>{
    TO_BE_REVIEWED (1),
    UNDER_REVIEW(2),
    HAS_TO_BE_REWORKED(3),
    APPROVED(4),
    NOT_APPROVED(5);

    private final int value;

    ThesisStatus(int val) {
        this.value = val;
    }

    @Override
    public ThesisStatus fromValue(int value) {
        for(ThesisStatus status : ThesisStatus.values()) {
            if(status.getValue() == value) {
                return status;
            }
        }

        throw new EnumValueNotAllowedException(String.format(Constants.ENUM_VALUE_NOT_ALLOWED_ERROR_MSG, "thesis status"));
    }

    public static ThesisStatus fromValueToEnum(int value) {
        for(ThesisStatus status : ThesisStatus.values()) {
            if(status.getValue() == value) {
                return status;
            }
        }

        throw new EnumValueNotAllowedException(String.format(Constants.ENUM_VALUE_NOT_ALLOWED_ERROR_MSG, "thesis status"));
    }
}
