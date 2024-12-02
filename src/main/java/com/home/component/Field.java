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
        if(key.getCode()==8){
            if(text.length()>0){
                text = text.substring(0,text.length()-1);
                Cursor.move(x+1+text.length(), y+1);
                System.out.println(" ");
            }
        }else{
            text+= (char) key.getCode();
        }
        drawText();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
