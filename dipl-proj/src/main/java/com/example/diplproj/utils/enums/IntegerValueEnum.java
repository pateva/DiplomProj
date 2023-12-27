package com.example.diplproj.utils.enums;

public interface IntegerValueEnum<E extends Enum<E>> {
    int getValue();

    E fromValue(int value);
}
