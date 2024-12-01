package com.home.component;

public class Cursor {
    public static void move(int x, int y){
        System.out.print("\033[" + y + ";" + x + "H");
    }
}
