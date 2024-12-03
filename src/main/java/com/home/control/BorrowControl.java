package com.home.control;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.home.component.Color;
import com.home.component.Cursor;
import com.home.dao.BookDao;
import com.home.dao.StudentDao;
import com.home.form.BorrowForm;
import com.home.form.GetBorrowForm;
import com.home.models.Book;
import com.home.models.Borrow;
import com.home.models.Student;
import com.home.util.DataProcess;

public class BorrowControl {
    private BorrowForm form;
    private List<Book> books;
    private List<Student> students;

    public BorrowControl(){
        init();
        control();
    }

    private void init(){
        form = new BorrowForm();
        books = new ArrayList<>();
        students = new ArrayList<>();
        books = BookDao.read();
        students = StudentDao.read();
        form.showData(getList());
    }
    private List<List<String>> getList(){
        List<List<String>> list = new ArrayList<>();
        List<Borrow> borrows;
        for(Student student : students){
            borrows = student.getBorrowedBook();
            if(borrows !=null && borrows.size()>0){
                for(Borrow borrow : borrows){
                    list.add(List.of(student.getId(), student.getName(), find(borrow.getId()).getId(),find(borrow.getId()).getTitle(), DataProcess.dateToStr(borrow.getDate()), check(borrow.getDate())));
                }
            }
        }   
        return list;
    }
    private String check(LocalDate date){
        int time =(int) ChronoUnit.DAYS.between(date, LocalDate.now());
        if(time>7) return Color.RED+"quá hạn"+Color.RESET;
        else if(time==7) return Color.YELLOW+"đang mượn"+Color.RESET;
        else return Color.GREEN+"đang mượn"+Color.RESET;
    }   
    private Book find(String id){
        for(Book book : books){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }

    private void control(){
        int code;
        while (true) { 
            code= form.control();
            switch(code) { 
                case 0:
                    borrow();
                    break;
                case 1:
                    give();
                    break;
                case 2:
                    checkOverTime();
                    break;
                case 3:
                    return;
                default :
                    break;                
            }
        }
        
    }
    private void borrow(){
        Cursor.clear();
        GetBorrowForm.borrow(books, students);
    }
    private void give(){
        Cursor.clear();
        GetBorrowForm.give(books, students);
    }
    private void checkOverTime(){
        Cursor.clear();
        List<List<String>> lists= GetBorrowForm.check(getList());
        form.showData(lists);
    }

    public static void main(String[] args) {
        Cursor.clear();
        new BorrowControl();
    }


}
