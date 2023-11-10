package com.example.project4.RUpizza;

public enum Size {

    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");
    private final String displayName;

    Size(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}


