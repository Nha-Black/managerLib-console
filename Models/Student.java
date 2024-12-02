package Models;

import java.time.LocalDate;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private String gender;
    private String phone;
    private String department;
    private String email;
    private LocalDate birthDay;
    private List<String> borrowBook;
    private int borrowLimit;
    

    public Student(String id, String name, String gender, String phone, String department, String email, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.department = department;
        this.email = email;
        this.birthDay = birthDay;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<String> getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(List<String> borrowBook) {
        this.borrowBook = borrowBook;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public void setBorrowLimit(int borrowLimit) {
        this.borrowLimit = borrowLimit;
    }

    @Override
    public String toString() {
        return "Student " + id + ", " + name + ", " + gender + ", " + phone + ", " + department + ", " + email + ", "
                + birthDay + ", " + borrowBook + ", " + borrowLimit;
    }
}
