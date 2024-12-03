package com.home.component;

public class Button extends Box{
    private String text;
    private String textColor=Color.RESET;

    public Button(int x, int y, String text) {
        super(x, y, text.length()+2, 2);
        setWidth(text.length()+2);
        this.text = text;
        
    }
    public void setText(String text){
        this.text= text;
        setWidth(text.length()+2);
    }
    public void setTextColor(String color){
        this.textColor = color;
        Cursor.move(x+1, y);
        System.out.print(textColor + text + Color.RESET);
    }
    public void setSelected(String color){
        setColor(color);
        setTextColor(color);
    }
    @Override
    public  void draw() {
        // super.draw();
        Cursor.move(x+1, y+1);
        System.out.print(textColor + text + Color.RESET);
        super.draw();
    }
  
}
