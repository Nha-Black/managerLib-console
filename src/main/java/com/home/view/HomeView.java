package com.home.view;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Box;
import com.home.component.Button;
import com.home.component.Color;
import com.home.component.Cursor;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Label;

public class HomeView {
    private Label title;
    private Button book;
    private Button student;
    private Button borrow;
    private Button exit;
    private Box box;
    private int index;
    private List<Button> buttons;
    // private Button selected;


    public HomeView() {
        init();
        // draw();
        // control();
    }
    
    private void init(){
        Cursor.hidden();
        title = new Label(0, 4, "Library");
        title.setTextColor(Color.PURPLE);
        book = new Button(5, 6, "Thông tin sách");
        student = new Button(5, 9, "Thông tin Sinh Viên");
        borrow = new Button(5, 12, "Thông tin mượn sách");
        exit = new Button(0, 15, "thoát");
        int maxWidth = book.getWidth();
        
        box =  new Box(2, 2, 0, 17);
        box.setColor(Color.GREEN);

        buttons = new ArrayList<>();
        buttons.add(book);
        buttons.add(student);
        buttons.add(borrow);
        buttons.add(exit);
        for(Button but : buttons){
            if(but.getWidth()>maxWidth) maxWidth= but.getWidth();
        }
        box.setWidth(maxWidth+4);
        for(Button but : buttons){
            but.setX(box.getX()+(box.getWidth()-but.getWidth())/2);
            
        }
        title.setX(box.getX()+(box.getWidth()-title.getText().length())/2);
        index =0;
        buttons.get(index).setSelected(Color.CYAN);
    }
    public void draw(){
        Cursor.topleft();
        title.draw();
        book.draw();
        student.draw();
        borrow.draw();
        exit.draw();
        box.draw();
    }
    private void controlMove(Key key){
        buttons.get(index).setSelected(Color.RESET);
        if(key==Key.UP) index = (index-1+4) % 4;
        else if(key==Key.DOWN) index = (index+1) % 4;
        buttons.get(index).setSelected(Color.CYAN);
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

    public int control(){
        Key key;
        while (true) { 
            key = KeyPress.Listen();
            if(key!=null){
                if(key==Key.UP || key==Key.DOWN){
                    controlMove(key);
                } else if(key==Key.ENTER){
                    return index;
                }
            }
        }
    }
    public static void main(String[] args) {
        Cursor.clear();
        new HomeView();
    }
}
