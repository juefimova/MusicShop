package com.example.music;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Res {
    INSTANCE;
    private ResourceBundle resourceBundle;

    public String getResourseName() {
        return resourseName;
    }

    private final String resourseName = "language";

    private Res() {
        resourceBundle = ResourceBundle.getBundle(resourseName, Locale.getDefault());
    }

    public void changeResourse(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourseName, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
