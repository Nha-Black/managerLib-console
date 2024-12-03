package com.home.control;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Cursor;
import com.home.dao.BookDao;
import com.home.form.BookForm;
import com.home.form.GetBookForm;
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
        int code;
        while (true) { 
            code= form.control();
            switch(code) { 
                case 0:
                    add();
                    break;
                case 1:
                    remove();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    return;
                default :
                    break;                
            }
        }
        
    }
    private void add(){
        Cursor.clear();
        Book book = GetBookForm.getData();
        books.add(book);
        BookDao.write(books);
        Cursor.clear();
        form.showData(books);
    }
    private void remove(){
        Cursor.clear();
        books= GetBookForm.remove(books);
        BookDao.write(books);
        Cursor.clear();
        form.showData(books);
    }
    private void update(){
        //get from form
        books= GetBookForm.update(books);
        BookDao.write(books);
    }

    public static void main(String[] args) {
        Cursor.clear();
        new BookControl();
    }


}
