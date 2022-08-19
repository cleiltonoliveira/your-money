package com.yourmoney.domain.model.types;

public enum Category {
    ALIMENTACAO("alimentacao"),
    SAUDE("saude"),
    MORADIA("moradia"),
    TRANSPORTE("transporte"),
    EDUCACAO("educacao"),
    LAZER("lazer"),
    IMPREVISTOS("imprevistos"),
    OUTRAS("outras");

    public final String category;
    Category(String value) {
        category = value;
    }
}