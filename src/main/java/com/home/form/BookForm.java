package com.home.form;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Button;
import com.home.component.Color;
import com.home.component.Cursor;
import com.home.component.Key;
import com.home.component.KeyPress;
import com.home.component.Table;
import com.home.models.Book;

public class BookForm {
    private Button add;
    private Button remove;
    private Button update;
    private Button back;
    private Table table;
    private int index;
    private List<Button> buttons;
    private final int number=4;

    public BookForm(){
        init();
        draw();
    }
    private void init(){
        Cursor.hidden();

        add = new Button(2, 2, "thêm sách");
        remove = new Button(add.getWidth()+add.getX()+3, 2, "xóa sách");
        update = new Button(remove.getX()+remove.getWidth()+3, 2, "tìm sách");
        back = new Button(update.getX()+update.getWidth()+3, 2, "quay lại");
        table = new Table(3, 6);
        buttons = new ArrayList<>();
        buttons.add(add);
        buttons.add(remove);
        buttons.add(update);
        buttons.add(back);
        index = 0 ;
        buttons.get(index).setSelected(Color.CYAN);
        table.setField_name(List.of("ID", "tên sách", "tác giả", "thể loại", "ngôn ngữ", "số lượng"));
    }

    public void draw(){
        Cursor.topleft();
        add.draw();
        remove.draw();
        update.draw();
        table.draw();
        back.draw();
    }

    public void showData(List<Book> books){
        table.clear();
        Cursor.clear();
        for(Book book : books){
            table.add(List.of(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getLanguage(), String.valueOf(book.getAvailable())));
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
        new BookForm().control();
    }
}
