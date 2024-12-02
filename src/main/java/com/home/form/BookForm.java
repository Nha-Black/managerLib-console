package com.home.form;

import java.util.List;

import com.home.component.Button;
import com.home.component.Table;

public class BookForm {
    private Button add;
    private Button remove;
    private Button search;
    private Table table;

    private BookForm(){
        init();
        draw();
    }
    private void init(){
        add = new Button(2, 2, "thêm sách");
        remove = new Button(add.getWidth()+add.getX()+3, 2, "xóa sách");
        search = new Button(remove.getX()+remove.getWidth()+3, 2, "tìm sách");
        table = new Table(3, 6);
        table.setField_name(List.of("ID", "tên sách", "tác giả", ""));
    }

    private void draw(){
        add.draw();
        remove.draw();
        search.draw();
    }
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        new BookForm();
    }
}
