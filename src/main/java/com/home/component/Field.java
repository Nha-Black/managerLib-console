package com.home.component;

public class Field extends Box {
    private String text;

    public Field(int x, int y, int width, int height) {       
        super(x, y, width, height);
        text="";
    }
    
    @Override
    public void draw() {
        drawText();
        super.draw();
    }
    public void drawText(){
        Cursor.move(x+1, y+1); 
        System.out.print(text);
    }
    public void enter(Key key){
        text+= (char) key.getCode();
        drawText();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
