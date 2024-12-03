package com.home.component;

public class Label {
    private int x;
    private int y;
    private String text;
    private String textColor;
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
    public Label(){}
    public Label(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        textColor= Color.RESET;
    }
    
    public void draw(){
        Cursor.move(x, y);
        System.out.println(Color.apply(textColor, text));
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    
}
