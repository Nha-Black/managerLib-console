package com.home.component;

public enum Key {
    ENTER(13),  
    ESC(27),
    LEFT(75),
    RIGHT(77),
    UP(72),
    DOWN(80),
    // BACKSPACE(8),
    DELETE(83),
    // SPACE(32),
    OTHER(0);

    private int code;

    Key(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        if (this == OTHER) {
            OTHER.code = code; 
        }
    }

    public static Key fromCode(int code) {
        for (Key key : Key.values()) {
            if (key.code == code) {
                return key;
            }
        }
        OTHER.setCode(code);
        return OTHER;
    }
}
