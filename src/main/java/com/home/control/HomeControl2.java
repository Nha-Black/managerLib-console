package com.home.control;

import java.util.ArrayList;
import java.util.List;

import com.home.dao.BookDao;
import com.home.dao.StudentDao;
import com.home.models.Book;
import com.home.models.Student;

public class HomeControl2 {
    private List<Book> books;
    private List<Student> students;


    public HomeControl2() {

    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    private void init(){
        books = new ArrayList<>();
        students = new ArrayList<>();
        books = BookDao.read();
        // students = StudentDao.read();
    }


    public void updateBook(List<Book> updatedBooks) {
        for (Book updatedBook : updatedBooks) {
            for (Book book : books) {
                if (book.getId().equals(updatedBook.getId())) {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPublisher(updatedBook.getPublisher());
                    book.setPublicationYear(updatedBook.getPublicationYear());
                    book.setLanguage(updatedBook.getLanguage());
                    book.setPageCount(updatedBook.getPageCount());
                }
            }
        }
    }

    public void updateStudent(List<Student> students) {
        this.students = students;
        StudentDao.write(students);
    }


    public boolean searchBook(Book targetBook) {
        for (Book book : books) {
            if (book.getId().equals(targetBook.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean searchStudent(Student targetStudent) {
        for (Student student : students) {
            if (student.getId().equals(targetStudent.getId())) {
                return true;
            }
        }
        return false;
    }
    

    public List<Student> checkOverTime() {
        List<Student> overTimeStudents = new ArrayList<>();
        for (Student student : students) {
            // if (student.getBorrowBook().size() > student.getBorrowLimit()) {
            //     overTimeStudents.add(student);
            // }
        }
        return overTimeStudents;
    }
}
