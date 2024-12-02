package com.home.component;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Box{
    public static final String RESET = "\033[0m";   // Đặt lại màu về mặc định
    public static final String RED = "\033[31m";   // Màu đỏ
    public static final String GREEN = "\033[32m"; // Màu xanh lá
    public static final String YELLOW = "\033[33m";// Màu vàng
    public static final String BLUE = "\033[34m";  // Màu xanh dương
    public static final String PURPLE = "\033[35m";// Màu tím
    public static final String CYAN = "\033[36m";  // Màu xanh cyan

    private List<String> items;
    private int index=0;
    public Menu(){
        super();
        items = new ArrayList<>();
    }
    public Menu(int x, int y) {
        super(x, y);
        items = new ArrayList<>();
        init();
    }
    public void addItem(String item){
        items.add(item);
        if (item.length()>width) width = item.length()+6;
        height=+1;
    } 
    public void display(){
        draw();
        for (int i=0; i<items.size(); i++){
            Cursor.move(x+3, y+1+i);
            if(i == index){
                System.out.print("\033[46m"+ items.get(index) + RESET);
            } else
            System.out.print(items.get(i));
        }
    }
    public int control(Key key){
        if(key==Key.DOWN){
            index = (index+1) % items.size();
        } else if(key==Key.UP){
            index = (index-1+items.size()) % items.size();
        } else if(key==Key.ENTER){
            return index;
        }
        draw();
        return -1; 
    }
    private void init(){
        addItem("sách");
        addItem("sinh viên");
        addItem("Mượn sách");
        display();
    
    }
    public static void main(String[] args) {
        new Menu(5,5);
    }
    
    
}
