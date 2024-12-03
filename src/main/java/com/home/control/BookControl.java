package com.home.control;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Cursor;
import com.home.dao.BookDao;
import com.home.form.BookForm;
import com.home.models.Book;

public class BookControl {
    private BookForm form;
    private List<Book> books;

    public BookControl(){
        init();
        control();
    }

    private void init(){
        form = new BookForm();
        books = new ArrayList<>();
        books = BookDao.read();
        form.draw();
        form.showData(books);
    }

    private void control(){
        form.control();
    }

    public static void main(String[] args) {
        Cursor.clear();
        new BookControl();
    }


}
