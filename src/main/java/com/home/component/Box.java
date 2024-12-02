package com.home.component;

public class Box {
    protected int x;
    protected  int y;
    protected  int width;
    protected  int height;
    protected String color = "\033[0m";
    
    public void setColor(String color){
        this.color=color;
    }
    public Box() {
    }
    
    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        width=0;
        height=2;
    }
    public Box(int x, int y, int width, int height) {
        this(x, y);
        this.width = width;
        this.height = height;
        
    }
    public void draw(){
        System.out.println(color);
        Cursor.move(x, y);
        System.out.print("┌");
        Cursor.move(x+width, y);
        System.out.print("┐");
        Cursor.move(x, y+height);
        System.out.print("└");
        Cursor.move(x+width, y+height);
        System.out.print("┘");
        for (int i=x+1; i < x+width; i++){
            Cursor.move(i, y);
            System.out.print("─");
            Cursor.move(i, y+height);
            System.out.print("─");
        }
        for (int i=y+1; i<y+height; i++){
            Cursor.move(x, i);
            System.out.print("│");
            Cursor.move(x+width, i);
            System.out.print("│");
            System.out.println();
        }
        System.out.println("\033[0m");
    }

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Box box = new Box(5,5,5,5);
        
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getColor() {
        return color;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    
}
