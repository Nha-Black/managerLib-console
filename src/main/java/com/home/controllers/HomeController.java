package control;

import models.Book;
import models.Student;

import java.util.ArrayList;
import java.util.List;

public class HomeControl {
    private List<Book> books;
    private List<Student> students;

    public HomeControl() {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addStudent(Student student) {
        students.add(student);
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

    public void updateStudent(List<Student> updatedStudents) {
        for (Student updatedStudent : updatedStudents) {
            for (Student student : students) {
                if (student.getId().equals(updatedStudent.getId())) {
                    student.setName(updatedStudent.getName());
                    student.setBirthDay(updatedStudent.getBirthDay());
                    student.setGender(updatedStudent.getGender());
                    student.setPhone(updatedStudent.getPhone());
                    student.setDepartment(updatedStudent.getDepartment());
                    student.setEmail(updatedStudent.getEmail());
                }
            }
        }
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
            if (student.getBorrowBook().size() > student.getBorrowLimit()) {
                overTimeStudents.add(student);
            }
        }
        return overTimeStudents;
    }
}
