package com.home.form;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Button;
import com.home.component.Color;
import com.home.component.Cursor;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Table;

public class BorrowForm {
    private Button borrow;
    private Button give;
    private Button check;
    private Button back;
    private Table table;
    private int index;
    private List<Button> buttons;
    private final int number=4;

    public BorrowForm(){
        init();
        draw();
    }
    private void init(){
        Cursor.hidden();

        borrow = new Button(2, 2, "mượn sách");
        give = new Button(borrow.getWidth()+borrow.getX()+3, 2, "trả sách");
        check = new Button(give.getX()+give.getWidth()+3, 2, "kiểm tra");
        back = new Button(check.getX()+check.getWidth()+3, 2, "quay lại");
        table = new Table(3, 6);
        buttons = new ArrayList<>();
        buttons.add(borrow);
        buttons.add(give);
        buttons.add(check);
        buttons.add(back);
        index = 0 ;
        buttons.get(index).setSelected(Color.CYAN);
        table.setField_name(List.of("ID","tên sinh viên", "ID", "tên sách", "thời gian mượn", "tình trạng"));
    }

    public void draw(){
        Cursor.topleft();
        borrow.draw();
        give.draw();
        check.draw();
        table.draw();
        back.draw();
    }

    public void showData(List<List<String>> borrows){
        table.clear();
        Cursor.clear();
        for(List<String> list : borrows){
            table.add(list);
        }
        draw();
    }
    private void controlMove(Key key){
        buttons.get(index).setSelected(Color.RESET);
        if(key==Key.RIGHT) index = (index+1) % number;
        else if(key==Key.LEFT) index = (index-1+number) % number;  
        buttons.get(index).setSelected(Color.CYAN);
        draw();
    }
    public int control(){
        Key key;
        while (true) { 
          key = KeyPress.Listen();
          if(key== Key.LEFT || key== Key.RIGHT){
            controlMove(key);
          }else if(key==Key.ENTER)
            return index;
        }
    }
    public static void main(String[] args) {
        Cursor.clear();
        new BorrowForm().control();
    }
}
