package com.example.DataCollection.enum_constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DataType {

    Vocab("Vocab"),
    Character("Character");
    private final String value;

    DataType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
