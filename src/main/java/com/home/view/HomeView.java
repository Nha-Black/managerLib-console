package com.home.view;

import com.home.component.Box;
import com.home.component.Button;
import com.home.component.Cursor;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Label;
import com.home.component.Menu;

public class HomeView {
    private Label title;
    private Menu menu;
    private Button book;
    private Button student;
    private Button borrow;
    private Button exit;
    private Box box;
    private int index;
    private Button selected;


    public HomeView() {
        init();
        draw();
        control();
    }
    
    private void init(){
        book = new Button(5, 2, "Thông tin sách");
        student = new Button(5, 5, "Thông tin Sinh Viên");
        borrow = new Button(5, 8, "Thông tin mượn sách");
        exit = new Button(0, 11, "thoát");
        int maxWidth = book.getWidth();
        if (student.getWidth()>maxWidth) maxWidth= student.getWidth();
        if (borrow.getWidth()>maxWidth) maxWidth= borrow.getWidth();
        box =  new Box(2, 1, maxWidth+4, 13);
        box.setColor("\033[32m");
        book.setX(box.getX()+(box.getWidth()-book.getWidth())/2);
        book.setColor("\033[34m");
        student.setX(box.getX()+(box.getWidth()-student.getWidth())/2);
        student.setColor("\033[34m");
        borrow.setX(box.getX()+(box.getWidth()-borrow.getWidth())/2);
        borrow.setColor("\033[34m");
        exit.setX(box.getX()+(box.getWidth()-exit.getWidth())/2);
        exit.setColor("\033[34m");

        System.out.println("\033[?25l");//ẩn con trỏ <> ?25h
        index =0;
        selected = book;
        selected.setTextColor("\033[35m");
    }
    public void draw(){
        System.out.print("\033[H");
        book.draw();
        student.draw();
        borrow.draw();
        exit.draw();
        box.draw();
    }
    private void controlMove(Key key){
        selected.setTextColor("\033[0m");
        if(key==Key.UP) index = (index-1+4) % 4;
        else if(key==Key.DOWN) index = (index+1) % 4;
        switch (index) {
            case 0:
                selected=book;
                break;
            case 1:
                selected=student;
                break;
            case 2:
                selected=borrow;
                break;
            case 3:
                selected=exit;
            default:
                break;
        }
        selected.setTextColor("\033[35m");
        draw();
    }
    
    private void controlSelected(){
        Cursor.move(0, 15);
        switch (index) {
            case 0:
                System.out.println("thông tin sách");
                break;
            case 1:
                System.out.println("thông tin sinh viên");
                break;
            case 2:
                System.out.println("thông tin mượn sách");
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void control(){
        Key key;
        while (true) { 
            key = KeyPress.Listen();
            if(key!=null){
                if(key==Key.UP || key==Key.DOWN){
                    controlMove(key);
                } else if(key==Key.ENTER){
                    controlSelected();
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        new HomeView();
    }
}
