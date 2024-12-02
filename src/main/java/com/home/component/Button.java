package com.home.component;

public class Button extends Box{
    private String text;
    private String textColor="\033[0m";

    public Button(int x, int y, String text) {
        super(x, y, text.length()+2, 2);
        this.text = text;
        
    }
    public void setText(String text){
        this.text= text;
    }
    public void setTextColor(String color){
        this.textColor = color;
        Cursor.move(x+1, y);
        System.out.print(textColor + text + "\033[0m");
    }
    @Override
    public  void draw() {
        // super.draw();
        Cursor.move(x+1, y+1);
        System.out.print(textColor + text + "\033[0m");
        super.draw();
    }
  
}
