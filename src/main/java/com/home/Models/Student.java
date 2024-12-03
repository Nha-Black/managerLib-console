
package com.home.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private String gender;
    private String phone;
    private String department;
    private String email;
    private LocalDate birthDay;
    private List<Borrow> borrowedBook;
    private final int borrowLimit = 5;
    
    public Student() {
    }

    public Student(String id, String name, String gender, String phone, String department, String email, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.department = department;
        this.email = email;
        this.birthDay = birthDay;
    }
    

    public Student(String id, String name, String gender, String phone, String department, String email,
            LocalDate birthDay, List<Borrow> borrowedBook) {
        this(id, name, gender, phone, department, email, birthDay);
        this.borrowedBook = borrowedBook;
    }

    public int borrow(Book book){
        if(borrowedBook.size()>borrowLimit) return -1;
        borrowedBook.add(new Borrow(book.getId(), LocalDate.now()));
        return 0;
    }

 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
    


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", birthDay=" + birthDay +
                ", borrowedBook=" + borrowedBook +
                '}';
    }

    public List<Borrow> getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(List<Borrow> borrowedBook) {
        this.borrowedBook = borrowedBook;
    }
}
