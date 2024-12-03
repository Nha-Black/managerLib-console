package com.home.component;

public class Cursor {
    
    public static void move(int x, int y){
        System.out.print("\033[" + y + ";" + x + "H");
    }
    public static void hidden(){
        System.out.println("\033[?25l");
    }

    public static void expose(){
        System.out.println("\033[?25h");
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
    }
    public static void topleft(){
        System.out.print("\033[H");
    }
}
