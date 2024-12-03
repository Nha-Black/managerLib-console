package com.home.form;

import java.util.List;

import com.home.component.Color;
import com.home.dao.BookDao;
import com.home.dao.StudentDao;
import com.home.models.Book;
import com.home.models.Student;

public class GetBorrowForm {
    public static void borrow(List<Book> books, List<Student> students){
        String idB,idS;
        idS= GetStudentForm.getID(students);
        idB= GetBookForm.getID(books);
        Book bo = new Book();
        for(Book book : books){
            if(book.getId().equals(idB)) {
                bo = book;
                break;
            }
        }
        
        for(Student student : students){
            if(student.getId().equals(idS)){
                student.borrow(idB);
                bo.setAvailable(bo.getAvailable()-1);           
                break;
            }
        }
        BookDao.write(books);
        StudentDao.write(students);
    }
    public static void give(List<Book> books, List<Student> students){
        String idB,idS;
        idS= GetStudentForm.getID(students);
        idB= GetBookForm.getID(books);
        Book bo = new Book();
        for(Book book : books){
            if(book.getId().equals(idB)) {
                bo = book;
                break;
            }
        }
        
        for(Student student : students){
            if(student.getId().equals(idS)){
                student.give(idB);
                bo.setAvailable(bo.getAvailable()+1);           
                break;
            }
        }
        BookDao.write(books);
        StudentDao.write(students);
    }
    public static List<List<String>> check(List<List<String>> lists){
        for(List<String> list : lists){
            if(list.get(list.size()-1).lastIndexOf(Color.GREEN)!=-1){
                lists.remove(list);
            };
        }
        return lists;
    }
}
