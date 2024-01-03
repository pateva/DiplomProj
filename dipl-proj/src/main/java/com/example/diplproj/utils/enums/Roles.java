package com.example.diplproj.utils.enums;

import lombok.Getter;

@Getter
public enum Roles {
    STUDENT("rol_rHKgD12NJqavNfar"),
    TEACHER("rol_lqmvzx0J2KvT8bWi");

    private final String val;

    Roles(String val){
        this.val = val;
    }
}
