package com.example.diplproj.utils.converters;

import com.example.diplproj.utils.enums.IntegerValueEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IntegerValueEnumConverter<E extends Enum<E> & IntegerValueEnum<E>> implements AttributeConverter<E, Integer> {
    private final Class<E> enumType;

    public IntegerValueEnumConverter(Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Integer convertToDatabaseColumn(E enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getValue();
    }

    @Override
    public E convertToEntityAttribute(Integer dbValue) {
        if (dbValue == null) {
            return null;
        }
        return enumType.getEnumConstants()[0].fromValue(dbValue);
    }
}
