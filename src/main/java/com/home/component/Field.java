package com.home.component;
import javax.swing.JTextField;
public class Field extends Box {
    private String text;

    public Field(int x, int y, int width, int height) {       
        super(x, y, width, height);
        text="";
    }
    
    @Override
    public void draw() {
        Cursor.move(x+1, y);
        System.out.print(text);
        super.draw();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
