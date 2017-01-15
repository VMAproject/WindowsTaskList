package com.tasklist.model;

import javafx.beans.property.SimpleStringProperty;

public enum DiffSign {
    NO_CHANGES("="),
    CHANGED("~"),
    ADDED("+"),
    REMOVED("-");

    private SimpleStringProperty sign;
    private SimpleStringProperty _name;

    DiffSign(String sign) {
        this.sign = new SimpleStringProperty(sign);
        this._name = new SimpleStringProperty(this.name());
    }

    public String getSign() {
        return sign.get();
    }

    public SimpleStringProperty signProperty() {
        return sign;
    }

    public SimpleStringProperty nameProperty() {
        return _name;
    }
}
