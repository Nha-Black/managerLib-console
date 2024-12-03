package com.home.form;

import java.util.List;

import com.home.component.DataEntry;
import com.home.models.Book;

public class GetBookForm<T> {
    public static Book getData(){
        DataEntry<Book> entry = new DataEntry<>(2, 2, Book.class);
        entry.add("ID", "id");
        entry.add("tên sách", "title");
        entry.add("tác giả", "author");
        entry.add("ngôn ngữ", "language");
        entry.add("thể loại", "genre");
        entry.add("nhà xuất bản", "publisher");
        entry.add("năm xuất bản", "publicationYear");
        entry.add("số lượng", "available");
        entry.add("số trang", "pageCount");
        
        entry.control();

        return entry.getclass();
    }
    public static String getID(List<Book> books){
        Boolean find = false;
        DataEntry entry = new DataEntry(2, 2);
        entry.add("ID", "id");
        do{
            entry.control();
            String id = entry.getData();
            entry.add("tên sách", "title");
            for(Book book : books){
                if(book.getId().equals(id)){
                    if(book.getAvailable()==0){
                        entry.setText("title", "hết sách", "ERROR");
                    }else{
                        entry.setText("title", book.getTitle());
                        entry.setButton("xác nhận");
                        entry.control();
                        find=true;
                        return id;
                    }
                    
                }
            }
            if(!find) entry.setText("title", "không tìm thấy", "ERROR");
        } while(!find);
        return null;
    }
    public static List<Book> remove(List<Book> books){
        Boolean find = false;
        DataEntry entry = new DataEntry(2, 2);
        entry.add("ID", "id");
        do{
            entry.control();
            String id = entry.getData();
            entry.add("tên sách", "title");
            for(Book book : books){
                if(book.getId().equals(id)){
                    entry.setText("title", book.getTitle());
                    entry.setButton("xác nhận xóa");
                    entry.control();
                    books.remove(book);
                    find=true;
                    break;
                }
            }
            if(!find) entry.setText("title", "không tìm thấy", "ERROR");
        } while(!find);
        return books;
    }
    public static List<Book> update(List<Book> books){
        boolean find = false;
        Book bo = new Book();
        DataEntry<Book> entry= new DataEntry<>(2, 2, Book.class);
        entry.add("ID", "id");
        //tìm kiếm sinh viên qua id
        do{
            entry.control();
            String id = entry.getData();
            entry.add("tên sách", "title");
            for(Book book : books){
                if(book.getId().equals(id)){
                    entry.setText("title", book.getTitle());
                    entry.setButton("chỉnh sửa");
                    entry.control();
                    bo= book;
                    find=true;
                    break;
                }
            }
            if(!find) entry.setText("title", "không tìm thấy", "ERROR");
        } while(!find);
        //chỉnh sửa thông tin
        entry.add("tác giả", "author");
        entry.add("ngôn ngữ", "language");
        entry.add("thể loại", "genre");
        entry.add("nhà xuất bản", "publisher");
        entry.add("năm xuất bản", "publicationYear");
        entry.add("số lượng", "available");
        entry.add("số trang", "pageCount");

        entry.control();
        bo = entry.getclass();
        
        //cập nhật vào list
        for(Book book : books){
            if(book.getId().equals(bo.getId())){
                books.set(books.indexOf(book), bo);
                break;
            }
        }
        return books;
    }
}
