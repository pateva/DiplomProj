package com.example.diplproj.utils.annotations;

import com.example.diplproj.utils.enums.Roles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRoles {
    Roles[] value();
}
