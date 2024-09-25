package com.riwi.artemisa.utils.enums;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    CATEGORY_NOT_FOUND("ERR_001", "Category not found"),
    INVALID_CATEGORY("ERR_002", "Invalid category name"),
    GENERIC_ERROR("ERR_003", "Generic error"),


    ;

    private final String code;
    private final String message;

    ErrorCatalog (String code, String message) {
        this.code = code;
        this.message = message;
    }
}
